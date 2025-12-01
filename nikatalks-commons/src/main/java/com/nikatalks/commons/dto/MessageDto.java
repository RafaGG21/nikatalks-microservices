package com.nikatalks.commons.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class MessageDto implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1139524006370300370L;
	private Long id;
    private String content;
    private LocalDateTime sentDate;
    private Long chatId;
    private String sender;

    public MessageDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getSentDate() {
        return sentDate;
    }

    public void setSentDate(LocalDateTime sentDate) {
        this.sentDate = sentDate;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
