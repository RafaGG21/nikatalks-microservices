package com.nikatalks.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nikatalks.commons.entity.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
	
	@Query("SELECT t FROM Teacher t WHERE t.email = :email")
    Optional<Teacher> findByEmail(String email);
}
