package com.nikatalks.service;

import java.util.List;
import java.util.stream.Collectors;

import org.bouncycastle.util.io.TeeInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nikatalks.commons.dto.ClassSessionDto;
import com.nikatalks.commons.entity.ClassSession;
import com.nikatalks.commons.entity.Student;
import com.nikatalks.commons.entity.Teacher;
import com.nikatalks.commons.mapper.GenericMapper;
import com.nikatalks.repository.ClassSessionRepository;

@Service
public class ClassSessionService {

    @Autowired
    private ClassSessionRepository classSessionRepository;

    public List<ClassSessionDto> getAllSessions() {
        List<ClassSession> sessions = classSessionRepository.findAll();
        return sessions.stream()
                .map(session -> GenericMapper.map(session, ClassSessionDto.class))
                .collect(Collectors.toList());
    }

    public ClassSessionDto getSessionById(Long id) {
        ClassSession session = classSessionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Session not found with id: " + id));
        return GenericMapper.map(session, ClassSessionDto.class);
    }

    public ClassSessionDto createSession(ClassSessionDto sessionDto) {
        ClassSession session = GenericMapper.map(sessionDto, ClassSession.class);
        Student student = new Student();
        student.setId(sessionDto.getStudentId());
        session.setStudent(student);
        Teacher teacher = new Teacher();
        teacher.setId(sessionDto.getTeacherId());
        session.setTeacher(teacher);
        session.setTeacherName(teacher.getFirstName() + " " + teacher.getLastName());
        ClassSession savedSession = classSessionRepository.save(session);
        return GenericMapper.map(savedSession, ClassSessionDto.class);
    }

    public ClassSessionDto updateSession(Long id, ClassSessionDto sessionDto) {
        ClassSession existing = classSessionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Session not found with id: " + id));

        // Actualiza campos individualmente o usa el mapper si deseas sobrescribir
        existing.setLanguage(sessionDto.getLanguage());
        existing.setDay(sessionDto.getDay());

        ClassSession updated = classSessionRepository.save(existing);
        return GenericMapper.map(updated, ClassSessionDto.class);
    }

    public void deleteSession(Long id) {
        classSessionRepository.deleteById(id);
    }
    
    public List<ClassSessionDto> findClassesByStudent(Long studentId) {
        List<ClassSession> sessions = classSessionRepository.findClassesByStudent(studentId);
        return sessions.stream()
                .map(session -> GenericMapper.map(session, ClassSessionDto.class))
                .collect(Collectors.toList());
    }

    public List<ClassSessionDto> findClassesByTeacher(Long teacherId) {
        List<ClassSession> sessions = classSessionRepository.findClassesByTeacher(teacherId);
        return sessions.stream()
                .map(session -> GenericMapper.map(session, ClassSessionDto.class))
                .collect(Collectors.toList());
    }

    public List<ClassSessionDto> findClassesByLanguage(String language) {
        List<ClassSession> sessions = classSessionRepository.findClassesByLanguage(language);
        return sessions.stream()
                .map(session -> GenericMapper.map(session, ClassSessionDto.class))
                .collect(Collectors.toList());
    }

}