package com.nikatalks.commons.dto;

public class UserDetailsDto {
	private Long id;
	private String email;
	private String role;
	private String firstName;
	private String lastName;
	private String image;
	private boolean hasSubscription;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public boolean isHasSubscription() {
		return hasSubscription;
	}

	public void setHasSubscription(boolean hasSubscription) {
		this.hasSubscription = hasSubscription;
	}

	
}
