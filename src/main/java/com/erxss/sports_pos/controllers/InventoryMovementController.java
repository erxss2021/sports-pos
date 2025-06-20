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

import com.erxss.sports_pos.dto.request.InventoryMovementRequest;
import com.erxss.sports_pos.dto.response.InventoryMovementResponse;
import com.erxss.sports_pos.service.InventoryMovementService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/inventory-movement")
@RequiredArgsConstructor
@Tag(name = "Inventory Movements", description = "Track stock changes")
public class InventoryMovementController {
	
	private final InventoryMovementService movementService;
	
	@GetMapping
	public ResponseEntity<List<InventoryMovementResponse>> findAll() {
		return ResponseEntity.ok(movementService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<InventoryMovementResponse> findById(@PathVariable Long id) {
		return movementService.findById(id)
				.map(ResponseEntity::ok)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	
	@GetMapping("/variant/{variantId}")
	public ResponseEntity<List<InventoryMovementResponse>> findByVariant(@PathVariable Long variantId) {
		return ResponseEntity.ok(movementService.findByProductVariant(variantId));
	}
	
	@PostMapping
	public ResponseEntity<InventoryMovementResponse> create(@RequestBody InventoryMovementRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(movementService.create(request));
	}

}
