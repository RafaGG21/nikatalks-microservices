package com.nikatalks.commons.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class ChatDto implements Serializable {


	private static final long serialVersionUID = 5084269753212036355L;
	
	private Long id;
    private Long studentId;
    private Long teacherId;
    private Date dateCreate;
    private List<MessageDto> listMessages;
    
    public ChatDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

	public List<MessageDto> getListMessages() {
		return listMessages;
	}

	public void setListMessages(List<MessageDto> listMessages) {
		this.listMessages = listMessages;
	}

	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}
    
    
}
