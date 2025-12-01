package com.nikatalks.service;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nikatalks.commons.dto.UserDetailsDto;
import com.nikatalks.commons.entity.Student;
import com.nikatalks.commons.entity.Teacher;
import com.nikatalks.repository.StudentRepository;
import com.nikatalks.repository.TeacherRepository;

@Service
public class UserRoleService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    public String getUserRole(String email) {
    	String role = StringUtils.EMPTY;
    	
        if (studentRepository.findByEmail(email).isPresent()) {
        	role =  "STUDENT";
        } else if (teacherRepository.findByEmail(email).isPresent()) {
        	role = "TEACHER";
        } else {
        	role = "USER_NOT_FOUND";
        }
        return role;
    }
    
    public UserDetailsDto getUserDetails(String email) {
        // Primero buscar en estudiantes
        Optional<Student> student = studentRepository.findByEmail(email);
        if (student.isPresent()) {
        	UserDetailsDto userDetails = new UserDetailsDto();
        	userDetails.setId(student.get().getId());
        	userDetails.setEmail(student.get().getEmail());
        	userDetails.setRole("STUDENT");
        	userDetails.setFirstName(student.get().getFirstName());
        	userDetails.setLastName(student.get().getLastName());
        	userDetails.setImage(student.get().getImage_key());
        	userDetails.setHasSubscription(student.get().getSubscription() != null && student.get().getDate_subscription() != null);
        	return userDetails;
        }
        
        // Si no es estudiante, buscar en profesores
        Optional<Teacher> teacher = teacherRepository.findByEmail(email);
        if (teacher.isPresent()) {
        	UserDetailsDto userDetails = new UserDetailsDto();
        	userDetails.setId(teacher.get().getId());
        	userDetails.setEmail(teacher.get().getEmail());
        	userDetails.setRole("TEACHER");
        	userDetails.setFirstName(teacher.get().getFirstName());
        	userDetails.setLastName(teacher.get().getLastName());
        	userDetails.setImage(teacher.get().getImage_key());
        	userDetails.setHasSubscription(false);
        	return userDetails;
        }
        
        // Si no existe en ninguna tabla
        throw new RuntimeException("Usuario no encontrado");
    }
}
