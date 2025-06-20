package com.erxss.sports_pos.service;

import java.util.List;
import java.util.Optional;

import com.erxss.sports_pos.dto.request.ProductVariantRequest;
import com.erxss.sports_pos.dto.response.ProductVariantResponse;

public interface ProductVariantService {

	ProductVariantResponse create(ProductVariantRequest request);
	List<ProductVariantResponse> findByProductId(Long productId);
	Optional<ProductVariantResponse> delete(Long id);
}
