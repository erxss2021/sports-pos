package com.erxss.sports_pos.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.erxss.sports_pos.dto.request.PurchaseRequest;
import com.erxss.sports_pos.dto.response.PurchaseResponse;
import com.erxss.sports_pos.enums.PurcheseStatus;
import com.erxss.sports_pos.service.PurchaseService;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/purchases")
@RequiredArgsConstructor
@Tag(name = "Purchases", description = "Operations for maniging product purchases")
public class PurchaseController {

	private final PurchaseService purchaseService;
	
	@GetMapping
	public ResponseEntity<List<PurchaseResponse>> findAll() {
		return ResponseEntity.ok(purchaseService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PurchaseResponse> findById(Long id) {
		return purchaseService.findById(id)
				.map(ResponseEntity::ok)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping
	public ResponseEntity<PurchaseResponse> create(@RequestBody PurchaseRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(purchaseService.create(request));
	}
	
	@PutMapping("/{id}/status")
	public ResponseEntity<PurchaseResponse> updateStatus(@PathVariable Long id, @RequestParam PurcheseStatus status) {
		return purchaseService.updateStatus(id, status)
				.map(ResponseEntity::ok)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<PurchaseResponse> delete(@PathVariable Long id) {
		return purchaseService.delete(id)
				.map(ResponseEntity::ok)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
}
