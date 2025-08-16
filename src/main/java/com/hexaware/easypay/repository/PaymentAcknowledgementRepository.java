package com.hexaware.easypay.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.easypay.entity.PaymentAcknowledgement;

public interface PaymentAcknowledgementRepository extends  JpaRepository<PaymentAcknowledgement, UUID>{

}
