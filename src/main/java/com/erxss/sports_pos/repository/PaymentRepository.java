package com.erxss.sports_pos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erxss.sports_pos.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

	List<Payment> findBySaleId(Long saleId);
}
