package com.erxss.sports_pos.service;

import java.util.List;
import java.util.Optional;

import com.erxss.sports_pos.dto.request.StockRequest;
import com.erxss.sports_pos.dto.response.StockResponse;

public interface StockService {
	List<StockResponse> findAll();
	Optional<StockResponse> findById(Long id);
	StockResponse addOrUpdateStock(StockRequest request);
	void increaseStock(Long storeId, Long variantId, Integer quantity);
	void decreaseStock(Long storeId, Long variantId, Integer quantity);
}
