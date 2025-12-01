package com.nikatalks.commons.dto;

public class PublicationDto {

    private Long id;

    private String title;

    private String body;

    private String language;
    private String level;
    private String competency;
    private String destination;
    private SubscriptionDto subscription;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getCompetency() {
		return competency;
	}

	public void setCompetency(String competency) {
		this.competency = competency;
	}

	
	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public SubscriptionDto getSubscription() {
		return subscription;
	}

	public void setSubscription(SubscriptionDto subscription) {
		this.subscription = subscription;
	}
    
    
}
