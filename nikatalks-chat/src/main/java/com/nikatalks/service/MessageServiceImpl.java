package com.nikatalks.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nikatalks.commons.dto.MessageDto;
import com.nikatalks.commons.entity.Chat;
import com.nikatalks.commons.entity.Message;
import com.nikatalks.commons.mapper.GenericMapper;
import com.nikatalks.repository.IChatRepository;
import com.nikatalks.repository.IMessageRepository;

@Service
public class MessageServiceImpl implements IMessageService{

	@Autowired
	private IMessageRepository messageRepository;
	
	@Autowired
	private IChatRepository chatRepositorio;
	
	@Override
	public MessageDto createMessage(MessageDto messageDto) {
		
		Chat chat = chatRepositorio.findById(messageDto.getChatId()).orElse(null);
		Message message = GenericMapper.map(messageDto, Message.class);
		message.setSentDate(new Date());
		message.setChat(chat);
		Message messageGuardado = messageRepository.save(message);
		return GenericMapper.map(messageGuardado, MessageDto.class);
	}

	@Override
	public List<MessageDto> getMessagesByChat(Long chatId) {
		return messageRepository.getMessagesByChat(chatId).stream()
				.map(Message -> GenericMapper.map(Message, MessageDto.class))
				.collect(Collectors.toList());
	}

}
