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

import com.erxss.sports_pos.dto.request.SizeRequest;
import com.erxss.sports_pos.dto.response.SizeResponse;
import com.erxss.sports_pos.service.SizeService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/sizes")
@RequiredArgsConstructor
@Tag(name = "Sizes", description = "Manage product Sizes")
public class SizeController {
	
	private final SizeService sizeService;
	
	@GetMapping
	public ResponseEntity<List<SizeResponse>> findAll() {
		return ResponseEntity.ok(sizeService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SizeResponse> findById(@PathVariable Long id) {
		return sizeService.findById(id)
				.map(ResponseEntity::ok)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping
	public ResponseEntity<SizeResponse> create(@RequestBody SizeRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(sizeService.create(request));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<SizeResponse> update(@PathVariable Long id, @RequestBody SizeRequest request) {
		return sizeService.update(id, request)
				.map(ResponseEntity::ok)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<SizeResponse> delete(@PathVariable Long id) {
		return sizeService.delete(id)
				.map(ResponseEntity::ok)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

}
