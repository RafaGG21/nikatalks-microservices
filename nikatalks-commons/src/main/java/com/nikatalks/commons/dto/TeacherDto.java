package com.nikatalks.commons.dto;

import java.io.Serializable;

public class TeacherDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8031777884678636951L;

	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String specialty;
	private String image_key;

	public TeacherDto() {
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public String getImage_key() {
		return image_key;
	}

	public void setImage_key(String image_key) {
		this.image_key = image_key;
	}

}
