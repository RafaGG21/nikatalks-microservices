package com.nikatalks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nikatalks.commons.entity.Message;

@Repository
public interface IMessageRepository extends JpaRepository<Message, Long>{

	@Query("SELECT m FROM Message m WHERE m.chat.id = :idChat")
	public List<Message> getMessagesByChat(Long idChat);

}