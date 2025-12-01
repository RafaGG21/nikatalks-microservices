package com.nikatalks.commons.dto;

public class PaymentRequest {
    private Long studentId;
    private Long classId;
    private Double amount;
    private String currency;
    
    // Constructors
    public PaymentRequest() {}
    
    public PaymentRequest(Long studentId, Long classId, Double amount, String currency) {
        this.studentId = studentId;
        this.classId = classId;
        this.amount = amount;
        this.currency = currency;
    }
    
    // Getters and Setters
    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }
    
    public Long getClassId() { return classId; }
    public void setClassId(Long classId) { this.classId = classId; }
    
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
    
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
}