package com.nikatalks.service;

import java.util.List;

import com.nikatalks.commons.dto.MessageDto;

public interface IMessageService {
	
	public MessageDto createMessage(MessageDto messageDto);
	public List<MessageDto> getMessagesByChat(Long chatId);
}
