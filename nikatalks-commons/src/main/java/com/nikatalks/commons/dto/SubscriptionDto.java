package com.nikatalks.commons.dto;

import java.math.BigDecimal;
import java.util.List;


public class SubscriptionDto {


    private Long id;

    private String name;

    private BigDecimal price;
    
    private List<FileDocDto> files;

    private List<PublicationDto> publications;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public List<FileDocDto> getFiles() {
		return files;
	}

	public void setFiles(List<FileDocDto> files) {
		this.files = files;
	}

	public List<PublicationDto> getPublications() {
		return publications;
	}

	public void setPublications(List<PublicationDto> publications) {
		this.publications = publications;
	}

}