package com.erxss.sports_pos.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.erxss.sports_pos.dto.request.PaymentRequest;
import com.erxss.sports_pos.dto.response.PaymentResponse;
import com.erxss.sports_pos.entity.Payment;
import com.erxss.sports_pos.repository.SaleRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PaymentMapper {

	private final SaleRepository saleRepository;
	
	public Payment toEntity(PaymentRequest dto) {
		Payment payment = new Payment();
		
		payment.setSale(saleRepository.findById(dto.getSaleId()).orElseThrow());
		payment.setAmount(dto.getAmount());
		payment.setMethod(dto.getMethod());
		payment.setTransactionId(dto.getTransactionId());
		payment.setPaymentDate(LocalDateTime.now());
		
		return payment;
	}
	
	public PaymentResponse toDto(Payment payment) {
		PaymentResponse dto = new PaymentResponse();
		
		dto.setId(payment.getId());
		dto.setSaleId(payment.getSale().getId());
		dto.setPaymentDate(payment.getPaymentDate());
		dto.setAmout(payment.getAmount());
		dto.setMethod(payment.getMethod());
		dto.setTransactionId(payment.getTransactionId());
		
		return dto;
	}
}
