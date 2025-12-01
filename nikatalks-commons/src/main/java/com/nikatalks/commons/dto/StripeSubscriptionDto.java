package com.nikatalks.commons.dto;

public class StripeSubscriptionDto {
    private String id;
    private String status;

    public StripeSubscriptionDto() {}

    public StripeSubscriptionDto(String id, String status) {
        this.id = id;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}