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

import com.erxss.sports_pos.dto.request.SportRequest;
import com.erxss.sports_pos.dto.response.SportResponse;
import com.erxss.sports_pos.service.SportService;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/sports")
@RequiredArgsConstructor
@Tag(name = "Sports", description = "Manage product sports")
public class SportController {
	
	private final SportService sportService;
	
	@GetMapping
	public ResponseEntity<List<SportResponse>> findAll() {
		return ResponseEntity.ok(sportService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SportResponse> findById(@PathVariable Long id) {
		return sportService.findById(id)
				.map(ResponseEntity::ok)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping
	public ResponseEntity<SportResponse> create(@RequestBody SportRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(sportService.create(request));
	}

	@PutMapping("/{id}")
	public ResponseEntity<SportResponse> update(@PathVariable Long id, @RequestBody SportRequest request) {
		return sportService.update(id, request)
				.map(ResponseEntity::ok)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<SportResponse> delete(@PathVariable Long id) {
		return sportService.delete(id)
				.map(ResponseEntity::ok)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
}
