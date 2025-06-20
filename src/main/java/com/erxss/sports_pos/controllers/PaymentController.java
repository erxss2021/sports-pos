package com.erxss.sports_pos.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.erxss.sports_pos.dto.request.PaymentRequest;
import com.erxss.sports_pos.dto.response.PaymentResponse;
import com.erxss.sports_pos.service.PaymentService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
@Tag(name = "Payments", description = "Register and manage payments for sales")
public class PaymentController {
	
	private final PaymentService paymentService;
	
	@GetMapping
	public ResponseEntity<List<PaymentResponse>> findAll() {
		return ResponseEntity.ok(paymentService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PaymentResponse> findById(@PathVariable Long id) {
		return paymentService.findById(id)
				.map(ResponseEntity::ok)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@GetMapping("/sale/{saleId}")
	public ResponseEntity<List<PaymentResponse>> findBySale(@PathVariable Long saleId) {
		return ResponseEntity.ok(paymentService.findBySale(saleId));
	}

	@PostMapping
	public ResponseEntity<PaymentResponse> create(@RequestBody PaymentRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.create(request));
	}
}
