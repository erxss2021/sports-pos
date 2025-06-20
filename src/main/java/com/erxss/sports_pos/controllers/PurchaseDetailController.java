package com.erxss.sports_pos.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.erxss.sports_pos.dto.request.PurchaseDetailRequest;
import com.erxss.sports_pos.dto.response.PurchaseDetailResponse;
import com.erxss.sports_pos.service.PurchaseDetailService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/purchase-details")
@RequiredArgsConstructor
@Tag(name = "Purchase Details", description = "Manage individual items in purchases")
public class PurchaseDetailController {
	
	private final PurchaseDetailService detailService;
	
	@GetMapping
	public ResponseEntity<List<PurchaseDetailResponse>> findAll() {
		return ResponseEntity.ok(detailService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PurchaseDetailResponse> findById(@PathVariable Long id) {
		return detailService.findById(id)
				.map(ResponseEntity::ok)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@GetMapping("/purchase/{purchaseId}")
	public ResponseEntity<List<PurchaseDetailResponse>> findByPurchase(@PathVariable Long purchaseId) {
		return ResponseEntity.ok(detailService.findByPurhase(purchaseId));
	}
	
	@PostMapping
	public ResponseEntity<PurchaseDetailResponse> create(@RequestBody PurchaseDetailRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(detailService.create(request));
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<PurchaseDetailResponse> delete(@PathVariable Long id) {
		return detailService.delete(id)
				.map(ResponseEntity::ok)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
}
