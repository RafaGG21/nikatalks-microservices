package com.nikatalks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nikatalks.commons.dto.ClassSessionDto;
import com.nikatalks.service.ClassSessionService;

@RestController
@RequestMapping("/class-sessions")
public class ClassSessionController {

	@Autowired
    private  ClassSessionService classSessionService;


    @GetMapping
    public List<ClassSessionDto> getAllClassSessions() {
        return classSessionService.getAllSessions();
    }

    @GetMapping("/{id}")
    public ClassSessionDto getClassSessionById(@PathVariable Long id) {
        return classSessionService.getSessionById(id);
    }

    @PostMapping
    public ClassSessionDto createClassSession(@RequestBody ClassSessionDto classSessionDto) {
        return classSessionService.createSession(classSessionDto);
    }

    @PutMapping("/{id}")
    public ClassSessionDto updateClassSession(@PathVariable Long id, @RequestBody ClassSessionDto classSessionDto) {
        return classSessionService.updateSession(id, classSessionDto);
    }

    @DeleteMapping("/{id}")
    public void deleteClassSession(@PathVariable Long id) {
        classSessionService.deleteSession(id);
    }

    @GetMapping("/student/{studentId}")
    public List<ClassSessionDto> findClassesByStudent(@PathVariable Long studentId) {
        return classSessionService.findClassesByStudent(studentId);
    }

    @GetMapping("/teacher/{teacherId}")
    public List<ClassSessionDto> findClassesByTeacher(@PathVariable Long teacherId) {
        return classSessionService.findClassesByTeacher(teacherId);
    }

    @GetMapping("/language/{language}")
    public List<ClassSessionDto> findClassesByLanguage(@PathVariable String language) {
        return classSessionService.findClassesByLanguage(language);
    }
}
