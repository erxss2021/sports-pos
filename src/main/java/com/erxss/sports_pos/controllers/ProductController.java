package com.erxss.sports_pos.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
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

import com.erxss.sports_pos.dto.request.ProductRequest;
import com.erxss.sports_pos.dto.response.ProductResponse;
import com.erxss.sports_pos.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
//@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
@Tag(name = "Products", description = "Product management for the sports store")
public class ProductController {
	
	private final ProductService productService;
	
	@GetMapping
	@Operation(summary = "Get all products")
	public ResponseEntity<List<ProductResponse>> getAll(){
		return ResponseEntity.ok(productService.findAll());
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Get product by ID")
	public ResponseEntity<ProductResponse> getById(@PathVariable Long id){
		return productService.findById(id)
				.map(ResponseEntity::ok)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, 
						"Product not found"));
	}
	
	@GetMapping("/search")
	@Operation(summary = "Search products by name")
	public ResponseEntity<List<ProductResponse>> searchByName(@RequestParam String name){
		return ResponseEntity.ok(productService.searchByName(name));
	}
	
	@PostMapping
	@Operation(summary = "Create a new product")
	public ResponseEntity<ProductResponse> create(@RequestBody ProductRequest request){
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(request));
	}
	
	@PutMapping("/{id}")
	@Operation(summary = "Update a product")
	public ResponseEntity<ProductResponse> update(@PathVariable Long id, @RequestBody ProductRequest request){
		return productService.update(id, request)
				.map(ResponseEntity::ok)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, 
						"Product not found"));
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Delete a product by ID")
	public ResponseEntity<ProductResponse> delete(@PathVariable Long id){
		return productService.delete(id)
				.map(ResponseEntity::ok)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, 
						"Product not found"));
	}

}
