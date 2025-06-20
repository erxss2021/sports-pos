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

import com.erxss.sports_pos.dto.request.SaleDetailRequest;
import com.erxss.sports_pos.dto.response.SaleDetailResponse;
import com.erxss.sports_pos.repository.SaleDetailService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/sale-details")
@RequiredArgsConstructor
@Tag(name = "Sale Details", description = "Manage items within a sale")
public class SaleDetailController {
	
	private final SaleDetailService detailService;
	
	@GetMapping
	public ResponseEntity<List<SaleDetailResponse>> findAll() {
		return ResponseEntity.ok(detailService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SaleDetailResponse> findById(@PathVariable Long id) {
		return detailService.findById(id)
				.map(ResponseEntity::ok)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@GetMapping("/sale/{saleId}")
	public ResponseEntity<List<SaleDetailResponse>> findBySale(@PathVariable Long saleId) {
		return ResponseEntity.ok(detailService.findbySale(saleId));
	}
	
	@PostMapping
	public ResponseEntity<SaleDetailResponse> create(@RequestBody SaleDetailRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(detailService.create(request));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<SaleDetailResponse> delete(@PathVariable Long id) {
		return detailService.delete(id)
				.map(ResponseEntity::ok)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

}
