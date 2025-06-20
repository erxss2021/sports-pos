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

import com.erxss.sports_pos.dto.request.CustomerRequest;
import com.erxss.sports_pos.dto.response.CustomerResponse;
import com.erxss.sports_pos.service.CustomerService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
@Tag(name = "Customers", description = "Customer management")
public class CustomerController {

	private final CustomerService customerService;
	
	@GetMapping
	public ResponseEntity<List<CustomerResponse>> findAll() {
		return ResponseEntity.ok(customerService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CustomerResponse> getById(@PathVariable Long id) {
		return customerService.findById(id)
				.map(ResponseEntity::ok)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<CustomerResponse>> searchByName(@RequestParam String name) {
		return ResponseEntity.ok(customerService.searchByname(name));
	}
	
	@PostMapping
	public ResponseEntity<CustomerResponse> create(@RequestBody CustomerRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(customerService.create(request));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CustomerResponse> update(@PathVariable Long id, @RequestBody CustomerRequest request) {
		return customerService.update(id, request)
				.map(ResponseEntity::ok)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<CustomerResponse> delete(@PathVariable Long id) {
		return customerService.delete(id)
				.map(ResponseEntity::ok)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
}
