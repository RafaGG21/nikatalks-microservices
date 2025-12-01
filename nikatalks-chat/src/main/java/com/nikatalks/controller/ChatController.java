package com.nikatalks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nikatalks.commons.dto.ChatDto;
import com.nikatalks.commons.dto.MessageDto;
import com.nikatalks.service.IChatService;
import com.nikatalks.service.IMessageService;


@RestController
@RequestMapping("/chat-messages")
public class ChatController {
	
	@Autowired
	private IChatService charService;

	@Autowired
	private IMessageService messageService;
	
	@Autowired
    private SimpMessagingTemplate messagingTemplate;
	
	@MessageMapping("/chat")
	@SendTo("/topic/messages")
	public MessageDto send(MessageDto message) throws Exception {
        MessageDto savedMessage = messageService.createMessage(message);
        messagingTemplate.convertAndSend("/topic/chat/" + message.getChatId(), savedMessage);      
        return savedMessage;
    }
	
	@GetMapping("/get-chat/{id}")
	public ResponseEntity<ChatDto> getChatById(@PathVariable Long id) {
		ChatDto chat = charService.getChatById(id);
		return chat != null ? ResponseEntity.ok(chat) : ResponseEntity.notFound().build();

	}
	
	@GetMapping("/get-chat/{student}/{teacher}")
	public ResponseEntity<ChatDto> getChatByStudentAndTeacher(@PathVariable Long student, @PathVariable Long teacher) {
		ChatDto chat = charService.getChatByStudentAndTeacher(student, teacher);
		return chat != null ? ResponseEntity.ok(chat) : ResponseEntity.notFound().build();

	}
	
	@GetMapping("/get-list-chat/{idStudent}")
	public ResponseEntity<List<ChatDto>> getListChatByStudent(@PathVariable Long idStudent) {
		List<ChatDto> listaChats = charService.getListChatByStudent(idStudent);
		return listaChats != null ? ResponseEntity.ok(listaChats) : ResponseEntity.notFound().build();
	}
	
	@GetMapping("/get-list-chat/{idTeacher}")
	public ResponseEntity<List<ChatDto>> getListChatByTeacher(@PathVariable Long idTeacher) {
		List<ChatDto> listaChats = charService.getListChatByStudent(idTeacher);
		return listaChats != null ? ResponseEntity.ok(listaChats) : ResponseEntity.notFound().build();
	}

	@PostMapping("/create-chat")
	public ResponseEntity<ChatDto> createChat(@RequestBody ChatDto chatDTO) {
		ChatDto chat = charService.createChat(chatDTO);
		return chat != null ? ResponseEntity.ok(chat) : ResponseEntity.notFound().build();
	}

	@PostMapping("/create-message")
	public ResponseEntity<MessageDto> createMessage(@RequestBody MessageDto mensajeDTO) {
		MessageDto mensajeCreado = messageService.createMessage(mensajeDTO);
		return mensajeCreado != null ? ResponseEntity.ok(mensajeCreado) : ResponseEntity.notFound().build();
	}
	
	@GetMapping("/getMessagesByChat/{chatId}")
	public ResponseEntity<List<MessageDto>> getMensajesByChat(@PathVariable Long chatId){
		List<MessageDto> mensajesChat = messageService.getMessagesByChat(chatId);
		return mensajesChat != null ? ResponseEntity.ok(mensajesChat) : ResponseEntity.notFound().build();
	}
}
