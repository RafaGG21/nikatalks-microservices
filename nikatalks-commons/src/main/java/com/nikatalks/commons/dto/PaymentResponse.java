package com.nikatalks.commons.dto;

public class PaymentResponse {
	private boolean success;
	private String message;
	private String transactionId;
	private String status;
	private Object data;

	// Constructors
	public PaymentResponse() {
	}

	public PaymentResponse(boolean success, String message) {
		this.success = success;
		this.message = message;
	}

	public PaymentResponse(boolean success, String message, String transactionId) {
		this.success = success;
		this.message = message;
		this.transactionId = transactionId;
	}

	// Getters and Setters
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}