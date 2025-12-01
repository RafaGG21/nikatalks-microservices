package com.nikatalks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nikatalks.commons.dto.TeacherDto;
import com.nikatalks.service.TeacherService;

@RestController
@RequestMapping("/auth/teachers")
public class TeacherController {

	@Autowired
    private  TeacherService teacherService;

    @PostMapping("/register")
    public ResponseEntity<TeacherDto> registerTeacher(@RequestBody TeacherDto request) {
        TeacherDto response = teacherService.registerTeacher(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TeacherDto>> getAll(){
    	final List<TeacherDto> listTeachers = teacherService.getAll();
        return ResponseEntity.ok(listTeachers);
    }
}
