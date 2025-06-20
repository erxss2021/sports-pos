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

import com.erxss.sports_pos.dto.request.SaleRequest;
import com.erxss.sports_pos.dto.response.SaleResponse;
import com.erxss.sports_pos.service.SaleService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
@Tag(name = "Sales", description = "Manage product sales")
public class SaleController {

	private final SaleService saleService;
	
	@GetMapping
	public ResponseEntity<List<SaleResponse>> findAll() {
		return ResponseEntity.ok(saleService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SaleResponse> findById(@PathVariable Long id) {
		return saleService.findById(id)
				.map(ResponseEntity::ok)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping
	public ResponseEntity<SaleResponse> create(@RequestBody SaleRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(saleService.create(request));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<SaleResponse> delete(@PathVariable Long id) {
		return saleService.delete(id)
				.map(ResponseEntity::ok)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
}
