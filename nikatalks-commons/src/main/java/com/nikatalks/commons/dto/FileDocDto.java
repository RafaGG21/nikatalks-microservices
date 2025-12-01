package com.nikatalks.commons.dto;

import java.io.Serializable;

public class FileDocDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 465512222518789949L;
	private Long id;
	private String fileName;
	private String contentType;
	private Long size;
	private String file_key;
	private String language;
	private String competence;
	private String level;
	private String destination;
	private String description;
	
	private SubscriptionDto subscription;
	
	// Constructor vacío
	public FileDocDto() {
	}

	// Getters y Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getFile_key() {
		return file_key;
	}

	public void setFile_key(String file_key) {
		this.file_key = file_key;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCompetence() {
		return competence;
	}

	public void setCompetence(String competence) {
		this.competence = competence;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
	public SubscriptionDto getSubscription() {
		return subscription;
	}

	public void setSubscription(SubscriptionDto subscription) {
		this.subscription = subscription;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
