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

import com.erxss.sports_pos.dto.request.ColorRequest;
import com.erxss.sports_pos.dto.response.ColorResponse;
import com.erxss.sports_pos.service.ColorService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/colors")
@RequiredArgsConstructor
@Tag(name = "Colors", description = "Manage product colors")
public class ColorController {

	private  final ColorService colorService;
	
	@GetMapping
	public ResponseEntity<List<ColorResponse>> findAll() {
		return ResponseEntity.ok(colorService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ColorResponse> findById(@PathVariable Long id) {
		return colorService.findById(id)
				.map(ResponseEntity::ok)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping
	public ResponseEntity<ColorResponse> create(@RequestBody ColorRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(colorService.create(request));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ColorResponse> update(@PathVariable Long id, @RequestBody ColorRequest request) {
		return colorService.update(id, request)
				.map(ResponseEntity::ok)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ColorResponse> delete(@PathVariable Long id) {
		return colorService.delete(id)
				.map(ResponseEntity::ok)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
}

