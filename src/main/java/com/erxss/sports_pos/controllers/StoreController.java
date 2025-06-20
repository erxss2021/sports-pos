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

import com.erxss.sports_pos.dto.request.StoreRequest;
import com.erxss.sports_pos.dto.response.StoreResponse;
import com.erxss.sports_pos.service.StoreService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
@Tag(name = "Stores", description = "Manage physical store locations")
public class StoreController {

	private final StoreService storeService;
	
	@GetMapping
	public ResponseEntity<List<StoreResponse>> findAll() {
		return ResponseEntity.ok(storeService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<StoreResponse> findById(@PathVariable Long id) {
		return storeService.findById(id)
				.map(ResponseEntity::ok)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping
	public ResponseEntity<StoreResponse> create(@RequestBody StoreRequest request)  {
		return ResponseEntity.status(HttpStatus.CREATED).body(storeService.create(request));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<StoreResponse> update(@PathVariable Long id, @RequestBody StoreRequest request) {
		return storeService.update(id, request)
				.map(ResponseEntity::ok)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<StoreResponse> delete(@PathVariable Long id) {
		return storeService.delete(id)
				.map(ResponseEntity::ok)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
}
