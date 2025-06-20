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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.erxss.sports_pos.dto.request.BrandRequest;
import com.erxss.sports_pos.dto.response.BrandResponse;
import com.erxss.sports_pos.service.BrandService;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/brands")
@RequiredArgsConstructor
@Tag(name = "Brands", description = "Manage products brands")
public class BrandController {
	
	private final BrandService brandService;
	
	@GetMapping
	public ResponseEntity<List<BrandResponse>> findAll() {
		return ResponseEntity.ok(brandService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<BrandResponse> findById(@PathVariable Long id) {
		return brandService.findById(id)
				.map(ResponseEntity::ok)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping
	public ResponseEntity<BrandResponse> create(@RequestBody BrandRequest request){ 
		return ResponseEntity.status(HttpStatus.CREATED).body(brandService.create(request));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<BrandResponse> update(@PathVariable Long id, @RequestBody BrandRequest request){
		return brandService.update(id, request)
				.map(ResponseEntity::ok)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<BrandResponse> delete(@PathVariable Long id){
		return brandService.delete(id)
				.map(ResponseEntity::ok)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
		
	
}
