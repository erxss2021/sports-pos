package com.erxss.sports_pos.service;

import java.util.List;
import java.util.Optional;

import com.erxss.sports_pos.dto.request.ProductRequest;
import com.erxss.sports_pos.dto.response.ProductResponse;

public interface ProductService {

	List<ProductResponse> findAll();
	Optional<ProductResponse> findById(Long id);
	List<ProductResponse> searchByName(String name);
	ProductResponse create(ProductRequest request);
	Optional<ProductResponse> update(Long id, ProductRequest request);
	Optional<ProductResponse> delete(Long id);
	
	
	
	
}
