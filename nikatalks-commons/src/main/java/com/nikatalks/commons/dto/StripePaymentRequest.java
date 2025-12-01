package com.nikatalks.commons.dto;

import java.util.Map;

public class StripePaymentRequest {
	private Long amount; 
	private String currency;
	private Map<String, String> metadata; 

	// Constructors
	public StripePaymentRequest() {
	}

	public StripePaymentRequest(Long amount, String currency, Map<String, String> metadata) {
		this.amount = amount;
		this.currency = currency;
		this.metadata = metadata;
	}

	// Getters and Setters
	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Map<String, String> getMetadata() {
		return metadata;
	}

	public void setMetadata(Map<String, String> metadata) {
		this.metadata = metadata;
	}
}