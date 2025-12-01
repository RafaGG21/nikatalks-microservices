package com.nikatalks.commons.dto;

import java.io.Serializable;
import java.util.Date;

public class StudentDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -984836350303827847L;

	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String image_key;
	private Date date_subscription;
	private SubscriptionDto subscriptionDto;
	private String idSubscriptionStripe;
	
	public StudentDto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDate_subscription() {
		return date_subscription;
	}

	public void setDate_subscription(Date date_subscription) {
		this.date_subscription = date_subscription;
	}

	public String getImage_key() {
		return image_key;
	}

	public void setImage_key(String image_key) {
		this.image_key = image_key;
	}

	public SubscriptionDto getSubscriptionDto() {
		return subscriptionDto;
	}

	public void setSubscriptionDto(SubscriptionDto subscriptionDto) {
		this.subscriptionDto = subscriptionDto;
	}

	public String getIdSubscriptionStripe() {
		return idSubscriptionStripe;
	}

	public void setIdSubscriptionStripe(String idSubscriptionStripe) {
		this.idSubscriptionStripe = idSubscriptionStripe;
	}

	
}
