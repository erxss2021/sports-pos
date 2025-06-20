package com.erxss.sports_pos.service;

import java.util.List;
import java.util.Optional;

import com.erxss.sports_pos.dto.request.InventoryMovementRequest;
import com.erxss.sports_pos.dto.response.InventoryMovementResponse;

public interface InventoryMovementService {

	List<InventoryMovementResponse> findAll();
	Optional<InventoryMovementResponse> findById(Long id);
	List<InventoryMovementResponse> findByProductVariant(Long variantId);
	InventoryMovementResponse create(InventoryMovementRequest request);
	
	
}
