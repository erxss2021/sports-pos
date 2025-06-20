package com.erxss.sports_pos.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erxss.sports_pos.dto.request.PaymentRequest;
import com.erxss.sports_pos.dto.response.PaymentResponse;
import com.erxss.sports_pos.entity.Payment;
import com.erxss.sports_pos.mapper.PaymentMapper;
import com.erxss.sports_pos.repository.PaymentRepository;
import com.erxss.sports_pos.service.PaymentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

	
	private final PaymentRepository paymentRepository;
	private final PaymentMapper mapper;
	
	@Override
	@Transactional(readOnly = true)
	public List<PaymentResponse> findAll() {
		
		return paymentRepository.findAll().stream().map(mapper::toDto).toList();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<PaymentResponse> findById(Long id) {
		
		return paymentRepository.findById(id).map(mapper::toDto);
	}

	@Override
	@Transactional(readOnly = true)
	public List<PaymentResponse> findBySale(Long saleId) {
		
		return paymentRepository.findBySaleId(saleId).stream().map(mapper::toDto).toList();
	}

	@Override
	@Transactional
	public PaymentResponse create(PaymentRequest request) {
		Payment payment = mapper.toEntity(request);
		return mapper.toDto(paymentRepository.save(payment));
	}

}
