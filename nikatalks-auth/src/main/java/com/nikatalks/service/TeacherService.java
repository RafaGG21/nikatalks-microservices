package com.nikatalks.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.nikatalks.commons.dto.TeacherDto;
import com.nikatalks.commons.entity.Teacher;
import com.nikatalks.commons.mapper.GenericMapper;
import com.nikatalks.repository.TeacherRepository;


@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public TeacherDto registerTeacher(TeacherDto request) {
        if (teacherRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }
        Teacher teacher = GenericMapper.map(request, Teacher.class);
        Teacher savedTeacher = teacherRepository.save(teacher);

        return GenericMapper.map(savedTeacher, TeacherDto.class);
    }
    
    public List<TeacherDto> getAll(){
    	final List<Teacher> listTeachers = teacherRepository.findAll();
    	return listTeachers.stream().map(teacher -> GenericMapper.map(teacher, TeacherDto.class))
    			.collect(Collectors.toList());
    }

}
