package com.erxss.sports_pos.service;

import java.util.List;
import java.util.Optional;

import com.erxss.sports_pos.dto.request.PaymentRequest;
import com.erxss.sports_pos.dto.response.PaymentResponse;

public interface PaymentService {

	List<PaymentResponse> findAll();
	Optional<PaymentResponse> findById(Long id);
	List<PaymentResponse> findBySale(Long saleId);
	
	PaymentResponse create(PaymentRequest request);
}
