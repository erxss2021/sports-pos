package com.erxss.sports_pos.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.erxss.sports_pos.dto.request.ProductVariantRequest;
import com.erxss.sports_pos.dto.response.ProductVariantResponse;
import com.erxss.sports_pos.service.ProductVariantService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/variants")
@RequiredArgsConstructor
@Tag(name = "Product Variants", description = "Manage product variants(Size, color, stock)")
//@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
public class ProductVariantController {
	
 private final ProductVariantService variantService;
 
 @Operation(summary = "Create a new product variant")
 @PostMapping
 public ResponseEntity<ProductVariantResponse> create(@RequestBody ProductVariantRequest request){
	 return ResponseEntity.status(HttpStatus.CREATED).body(variantService.create(request));
 }
 
 @Operation(summary = "Get all variants by product ID")
 @GetMapping("/product/{productId}")
 public ResponseEntity<List<ProductVariantResponse>> getByProductId(@PathVariable Long productId){
	 return ResponseEntity.ok(variantService.findByProductId(productId));
 }

 @Operation(summary = "Delete a product variant by ID")
 @DeleteMapping("/{id}")
 public ResponseEntity<ProductVariantResponse> delete(@PathVariable Long id){
	 return variantService.delete(id)
			 .map(ResponseEntity::ok)
			 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Variant not found"));
 }
}
