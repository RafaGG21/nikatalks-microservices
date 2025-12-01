package com.nikatalks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nikatalks.commons.entity.Subscription;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long>{

}
