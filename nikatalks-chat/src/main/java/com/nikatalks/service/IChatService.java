package com.nikatalks.service;

import java.util.List;

import com.nikatalks.commons.dto.ChatDto;

public interface IChatService {
	
	public ChatDto createChat(ChatDto chatDTO);
	public ChatDto getChatByStudentAndTeacher(Long idStudent, Long idTeacher);
	public List<ChatDto> getListChatByTeacher(Long idTeacher);
	public List<ChatDto> getListChatByStudent(Long idStudent);
	public ChatDto getChatById(Long id);
}
