package com.erxss.sports_pos.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.erxss.sports_pos.dto.request.InventoryMovementRequest;
import com.erxss.sports_pos.dto.response.InventoryMovementResponse;
import com.erxss.sports_pos.entity.InventoryMovement;
import com.erxss.sports_pos.repository.ProductVariantRepository;
import com.erxss.sports_pos.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class InventoryMovementMapper {

	private final ProductVariantRepository variantRepository;
	private final UserRepository userRepository;
	
	public InventoryMovement toEntity(InventoryMovementRequest dto) {
		
		InventoryMovement movement = new InventoryMovement();
		
		movement.setProductVariant(variantRepository.findById(dto.getProductVariantId())
				.orElseThrow(() -> new EntityNotFoundException("ProductVariant not found")));
		
		movement.setType(dto.getType());
		movement.setQuantity(dto.getQuantity());
		movement.setReason(dto.getReason());
		movement.setUser(userRepository.findById(dto.getUserId())
				.orElseThrow(() -> new EntityNotFoundException("User not found")));
		movement.setDate(LocalDateTime.now());
		
		return movement;
	}
	
	public InventoryMovementResponse toDto(InventoryMovement movement) {
		InventoryMovementResponse dto = new InventoryMovementResponse();
		
		dto.setId(movement.getId());
		dto.setProductName(movement.getProductVariant().getProduct().getName());
		
		dto.setVariantAttributes(
				"Color: " + movement.getProductVariant().getColor().getName() + 
				", Size: " + movement.getProductVariant().getSize().getLabel()
				);
		
		dto.setType(movement.getType());
		dto.setQuantity(movement.getQuantity());
		dto.setReason(movement.getReason());
		dto.setDate(movement.getDate());
		dto.setUserName(movement.getUser().getUsername());
		return dto;
	}
}
