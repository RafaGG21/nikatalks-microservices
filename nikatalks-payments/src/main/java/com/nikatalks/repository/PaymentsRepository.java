package com.nikatalks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nikatalks.commons.entity.Payment;

@Repository
public interface PaymentsRepository extends JpaRepository<Payment, Long>{

}
