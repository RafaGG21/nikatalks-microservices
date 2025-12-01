package com.nikatalks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nikatalks.commons.entity.Chat;



@Repository
public interface IChatRepository extends JpaRepository<Chat, Long>{

	@Query("SELECT c FROM Chat c WHERE c.student.id = :idStudent AND c.teacher.id = :idTeacher ")
    public Chat getChatByStudentAndTeacher(Long idStudent, Long idTeacher);

	@Query("SELECT c FROM Chat c WHERE c.teacher.id = :idTeacher ")
    public List<Chat> getListchatsByTeacher(Long idTeacher);
	
	@Query("SELECT c FROM Chat c WHERE c.student.id = :idStudent ")
    public List<Chat> getListchatsByStudent(Long idStudent);
}

