package com.nikatalks.service;


import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nikatalks.commons.dto.StudentDto;
import com.nikatalks.commons.dto.SubscriptionDto;
import com.nikatalks.commons.dto.TeacherDto;
import com.nikatalks.commons.entity.Student;
import com.nikatalks.commons.entity.Subscription;
import com.nikatalks.commons.entity.Teacher;
import com.nikatalks.commons.mapper.GenericMapper;
import com.nikatalks.controller.FilesServiceClient;
import com.nikatalks.repository.StudentRepository;
import com.nikatalks.repository.SubscriptionRepository;
import com.nikatalks.repository.TeacherRepository;

import feign.FeignException;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private  SubscriptionRepository subscriptionRepository;

    @Autowired
    private FilesServiceClient filesServiceClient;
    
    @Autowired
    private TeacherRepository teacherRepository;
    
    
    public StudentDto registerStudent(Student request) {
        if (studentRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        Student student = new Student();
        student.setEmail(request.getEmail());

        Student savedStudent = studentRepository.save(student);

        StudentDto studentDto = new StudentDto();
        studentDto.setId(savedStudent.getId());
        studentDto.setEmail(savedStudent.getEmail());
        return studentDto;
    }
    
    public StudentDto findByEmail(String  email) {
    	StudentDto studentDto = null;
    	Optional<Student> studentOpt = studentRepository.findByEmail(email);
        if (!studentOpt.isPresent()) {
            throw new RuntimeException("user no exists");
        }
        studentDto = GenericMapper.map(studentOpt.get(), StudentDto.class); 
        return studentDto;
    }
    
    public StudentDto assignSubscription(Long studentId, Long subscriptionId, String idSubscriptionStripe) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new RuntimeException("Subscription not found"));

        student.setSubscription(subscription);
        student.setDate_subscription(new Date());
        student.setIdSubscriptionStripe(idSubscriptionStripe);
        Student saved = studentRepository.save(student);

        return GenericMapper.map(saved, StudentDto.class);
    }
    
    public StudentDto cancelSubscription(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        student.setSubscription(null);
        student.setDate_subscription(null);
        student.setIdSubscriptionStripe(null);
        Student saved = studentRepository.save(student);

        return GenericMapper.map(saved, StudentDto.class);
    }

    public StudentDto updateStudent(Long id, StudentDto studentDto) {
    	StudentDto studentReturn = null;
    	Optional<Student> studentBd = studentRepository.findById(id);
    	
    	if (studentBd.isPresent()) {
    		studentReturn = new StudentDto();
    		studentReturn.setId(studentBd.get().getId());
    		studentReturn.setFirstName(studentBd.get().getFirstName());
    		studentReturn.setLastName(studentBd.get().getLastName());
    		studentReturn.setEmail(studentBd.get().getLastName());
    		studentReturn.setDate_subscription(studentBd.get().getDate_subscription());
    		studentReturn.setImage_key(studentBd.get().getImage_key());
    		//studentReturn.setSubscriptionDto(GenericMapper.map(studentBd.get().getSubscription(), SubscriptionDto.class));
    		studentRepository.save(studentBd.get());
    	}
    	return studentReturn;
    }
    
    public boolean uploadImage(MultipartFile file, String email) throws IOException {
    	boolean ok = false;
        try {
        	Optional<Student> student = studentRepository.findByEmail(email);
            if (student.isPresent()) {
            	String image = filesServiceClient.uploadImage(file);
				if (StringUtils.isNotEmpty(image)) {
					student.get().setImage_key(image);
					studentRepository.save(student.get());
					ok = true;
				}
				return ok;
			}
            
            Optional<Teacher> teacher = teacherRepository.findByEmail(email);
            if (teacher.isPresent()) {
            	String image = filesServiceClient.uploadImage(file);
				if (StringUtils.isNotEmpty(image)) {
					teacher.get().setImage_key(image);
					teacherRepository.save(teacher.get());
					ok = true;
				}
				return ok;
			}
        } catch (FeignException e) {
            throw new IOException("Error al subir imagen al servicio de archivos: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new IOException("Error inesperado al subir imagen: " + e.getMessage(), e);
        }
        return ok;
    }

    public String getImageUrl(String imageKey) {
        if (imageKey == null || imageKey.isEmpty()) {
            return null;
        }
        
        try {
            return filesServiceClient.getImageUrl(imageKey);
        } catch (FeignException e) {
            System.err.println("Error al obtener URL de imagen: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.err.println("Error inesperado al obtener URL de imagen: " + e.getMessage());
            return null;
        }
    }
    
    public List<StudentDto> getAll(){
    	final List<Student> listStudents = studentRepository.findAll();
    	return listStudents.stream().map(student -> GenericMapper.map(student, StudentDto.class))
    			.collect(Collectors.toList());
    }
}
