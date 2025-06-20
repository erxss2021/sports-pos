package com.erxss.sports_pos.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erxss.sports_pos.dto.request.InventoryMovementRequest;
import com.erxss.sports_pos.dto.response.InventoryMovementResponse;
import com.erxss.sports_pos.entity.InventoryMovement;
import com.erxss.sports_pos.mapper.InventoryMovementMapper;
import com.erxss.sports_pos.repository.InventoryMovementRepository;
import com.erxss.sports_pos.service.InventoryMovementService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryMovementServiceImpl implements InventoryMovementService {
	
	private final InventoryMovementRepository movementRepository;
	private final InventoryMovementMapper mapper;

	@Override
	@Transactional(readOnly = true)
	public List<InventoryMovementResponse> findAll() {
		
		return movementRepository.findAll()
				.stream()
				.map(mapper::toDto)
				.toList();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<InventoryMovementResponse> findById(Long id) {
		
		return movementRepository.findById(id).map(mapper::toDto);
	}

	@Override
	@Transactional(readOnly = true)
	public List<InventoryMovementResponse> findByProductVariant(Long variantId) {
		
		return movementRepository.findByProductVariantId(variantId)
				.stream()
				.map(mapper::toDto)
				.toList();
	}

	@Override
	@Transactional
	public InventoryMovementResponse create(InventoryMovementRequest request) {
		InventoryMovement movement = mapper.toEntity(request);
		return mapper.toDto(movementRepository.save(movement));
	}

}
