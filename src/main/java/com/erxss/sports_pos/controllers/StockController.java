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

import com.erxss.sports_pos.dto.request.StockRequest;
import com.erxss.sports_pos.dto.response.StockResponse;
import com.erxss.sports_pos.service.StockService;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/stock")
@RequiredArgsConstructor
@Tag(name = "Stock", description = "Inventory management per store")
public class StockController {
	
	private final StockService stockService;

	@GetMapping
	public ResponseEntity<List<StockResponse>> findAll() {
		return ResponseEntity.ok(stockService.findAll());
	}
	
	public ResponseEntity<StockResponse> findById(@PathVariable Long id) {
		return stockService.findById(id)
				.map(ResponseEntity::ok)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping
	public ResponseEntity<StockResponse> addOrUpdate(@RequestBody StockRequest request){
		return ResponseEntity.ok(stockService.addOrUpdateStock(request));
	}
}
