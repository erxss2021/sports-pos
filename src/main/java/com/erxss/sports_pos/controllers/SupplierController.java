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

import com.erxss.sports_pos.dto.request.SupplierRequest;
import com.erxss.sports_pos.dto.response.SupplierResponse;
import com.erxss.sports_pos.service.SupplierService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/suppliers")
@RequiredArgsConstructor
@Tag(name = "Suppliers", description = "Supplier management")
public class SupplierController {
	
	private final SupplierService supplierService;
	
	@GetMapping
	public ResponseEntity<List<SupplierResponse>> findAll() {
		return ResponseEntity.ok(supplierService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SupplierResponse> findById(Long id) {
		return supplierService.findById(id)
				.map(ResponseEntity::ok)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<SupplierResponse>> searchByName(@RequestParam String name) {
		return ResponseEntity.ok(supplierService.searchName(name));
	}

	
	@PostMapping
	public ResponseEntity<SupplierResponse> create(@RequestBody SupplierRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(supplierService.create(request));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<SupplierResponse> update(@PathVariable Long id, @RequestBody SupplierRequest request) {
		return supplierService.update(id, request)
				.map(ResponseEntity::ok)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<SupplierResponse> delete(@PathVariable Long id) {
		return supplierService.delete(id)
				.map(ResponseEntity::ok)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
}
