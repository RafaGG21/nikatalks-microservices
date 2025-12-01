package com.nikatalks.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nikatalks.commons.entity.ClassSession;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface ClassSessionRepository extends JpaRepository<ClassSession, Long> {

    @Query("SELECT c FROM ClassSession c WHERE c.student.id = :studentId")
    List<ClassSession> findClassesByStudent(Long studentId);
    
    @Query("SELECT c FROM ClassSession c WHERE c.teacher.id = :teacherId")
    List<ClassSession> findClassesByTeacher(Long teacherId);
    
    @Query("SELECT c FROM ClassSession c WHERE c.language = :language")
    List<ClassSession> findClassesByLanguage(String language);
}