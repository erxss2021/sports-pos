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

import com.erxss.sports_pos.dto.request.CategoryRequest;
import com.erxss.sports_pos.dto.response.CategoryResponse;
import com.erxss.sports_pos.service.CategoryService;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@Tag(name = "Categories", description = "Manage product categories")
public class CategoryController {
	
	private final CategoryService categoryService;
	
	@GetMapping
	public ResponseEntity<List<CategoryResponse>> findAll() {
		return ResponseEntity.ok(categoryService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoryResponse> findById(@PathVariable Long id) {
		return categoryService.findById(id)
				.map(ResponseEntity::ok)
				.orElseThrow(() -> new 
						ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping
	public ResponseEntity<CategoryResponse> create(@RequestBody CategoryRequest request){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(categoryService.create(request));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CategoryResponse> update(@PathVariable Long id, @RequestBody CategoryRequest request){
		return categoryService.update(id, request)
				.map(ResponseEntity::ok)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<CategoryResponse> delete(@PathVariable Long id){
		return categoryService.delete(id)
				.map(ResponseEntity::ok)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

}
