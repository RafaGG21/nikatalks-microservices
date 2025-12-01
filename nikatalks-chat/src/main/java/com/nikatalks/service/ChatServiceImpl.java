package com.nikatalks.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nikatalks.commons.dto.ChatDto;
import com.nikatalks.commons.dto.MessageDto;
import com.nikatalks.commons.entity.Chat;
import com.nikatalks.commons.entity.Message;
import com.nikatalks.commons.mapper.GenericMapper;
import com.nikatalks.repository.IChatRepository;
import com.nikatalks.repository.IMessageRepository;

@Service
public class ChatServiceImpl implements IChatService{

	@Autowired
	private IChatRepository chatRepository;

	@Autowired
	private IMessageRepository messageRepository;
	
	@Override
	public ChatDto createChat(ChatDto chatDTO) {
		ChatDto chatDTORetornar = null;
		 try {
			Chat chat = GenericMapper.map(chatDTO, Chat.class);
			chat.setListMessages(new ArrayList<>());
			chat.setDateCreate(new Date());
			Chat chatGuardado = chatRepository.save(chat);
			chatDTORetornar = GenericMapper.map(chatGuardado, ChatDto.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return chatDTORetornar;
	}

	@Override
	public ChatDto getChatByStudentAndTeacher(Long idStudent, Long idTeacher) {
		ChatDto chatDTO = null; 
		try {
			final Chat chat = chatRepository.getChatByStudentAndTeacher(idStudent, idTeacher);
			if (chat != null) {
				chatDTO = GenericMapper.map(chat, ChatDto.class);
				List<Message> mensajes = messageRepository.getMessagesByChat(chatDTO.getId());
				if (mensajes != null && !mensajes.isEmpty()) {
					List<MessageDto> listaMessagesDTO = mensajes.stream()
							.map(mensaje -> GenericMapper.map(mensaje, MessageDto.class)).collect(Collectors.toList());
					chatDTO.setListMessages(listaMessagesDTO);
				}
			} else {
				return null;
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return chatDTO;
	}

	@Override
	public List<ChatDto> getListChatByTeacher(Long idTeacher) {
		List<ChatDto> listaDTO = null;
		try {
			final List<Chat> listaChats = chatRepository.getListchatsByTeacher(idTeacher);
			if (listaChats != null) {
				listaDTO = listaChats.stream().map(chat -> GenericMapper.map(chat, ChatDto.class))
						.collect(Collectors.toList());
				for (ChatDto chat : listaDTO) {
					List<Message> mensajes = messageRepository.getMessagesByChat(chat.getId());
					if (mensajes != null && !mensajes.isEmpty()) {
						List<MessageDto> listaMessagesDTO = mensajes.stream()
								.map(mensaje -> GenericMapper.map(mensaje, MessageDto.class))
								.collect(Collectors.toList());
						chat.setListMessages(listaMessagesDTO);
					}
				}
				
			} else {
				return null;
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaDTO;
	}

	@Override
	public List<ChatDto> getListChatByStudent(Long idStudent) {
		List<ChatDto> listaDTO = null;
		try {
			final List<Chat> listaChats = chatRepository.getListchatsByStudent(idStudent);
			if (listaChats != null) {
				listaDTO = listaChats.stream().map(chat -> GenericMapper.map(chat, ChatDto.class))
						.collect(Collectors.toList());
				for (ChatDto chat : listaDTO) {
					List<Message> mensajes = messageRepository.getMessagesByChat(chat.getId());
					if (mensajes != null && !mensajes.isEmpty()) {
						List<MessageDto> listaMessagesDTO = mensajes.stream()
								.map(mensaje -> GenericMapper.map(mensaje, MessageDto.class))
								.collect(Collectors.toList());
						chat.setListMessages(listaMessagesDTO);
					}
				}
				
			} else {
				return null;
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaDTO;
	}

	@Override
	public ChatDto getChatById(Long id) {
		ChatDto chatDTO = null;
		try {
			chatDTO = GenericMapper.map(chatRepository.findById(id).orElse(null), ChatDto.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return chatDTO;
	}

}
