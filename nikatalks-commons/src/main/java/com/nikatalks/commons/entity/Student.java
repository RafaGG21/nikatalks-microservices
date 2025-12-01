package com.nikatalks.commons.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "students")
public class Student implements Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = -4816436300867799370L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String image_key;
    private Date date_subscription;
    
    @Column(name="id_subscription_stripe")
    private String idSubscriptionStripe;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscription_id", referencedColumnName = "id")
    private Subscription subscription;
    
    public Student() {}

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

	public String getImage_key() {
		return image_key;
	}

	public void setImage_key(String image_key) {
		this.image_key = image_key;
	}

	public Date getDate_subscription() {
		return date_subscription;
	}

	public void setDate_subscription(Date date_subscription) {
		this.date_subscription = date_subscription;
	}

	public Subscription getSubscription() {
		return subscription;
	}

	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}

	public String getIdSubscriptionStripe() {
		return idSubscriptionStripe;
	}

	public void setIdSubscriptionStripe(String idSubscriptionStripe) {
		this.idSubscriptionStripe = idSubscriptionStripe;
	}
    
	
    

}
