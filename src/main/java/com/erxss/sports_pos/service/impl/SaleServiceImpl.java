package com.erxss.sports_pos.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erxss.sports_pos.dto.request.InventoryMovementRequest;
import com.erxss.sports_pos.dto.request.SaleRequest;
import com.erxss.sports_pos.dto.response.SaleResponse;
import com.erxss.sports_pos.entity.Sale;
import com.erxss.sports_pos.entity.SaleDetail;
import com.erxss.sports_pos.enums.MovementType;
import com.erxss.sports_pos.mapper.SaleMapper;
import com.erxss.sports_pos.repository.SaleRepository;
import com.erxss.sports_pos.service.InventoryMovementService;
import com.erxss.sports_pos.service.SaleService;
import com.erxss.sports_pos.service.StockService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {

	
	private final SaleRepository saleRepository;
	private final InventoryMovementService movementService;
	private final SaleMapper mapper;
	private final StockService stockService;
	
	@Override
	@Transactional(readOnly = true)
	public List<SaleResponse> findAll() {
	
		return saleRepository.findAll().stream().map(mapper::toDto).toList();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<SaleResponse> findById(Long id) {
		
		return saleRepository.findById(id).map(mapper::toDto);
	}

	@Override
	@Transactional
	public SaleResponse create(SaleRequest request) {
		Sale sale = mapper.toEntity(request);
		Sale saved = saleRepository.save(sale);
		
		//Descontar del stock
		for(SaleDetail detail: saved.getDetails()) {
			
			Long variantId = detail.getProductVariant().getId();
			Integer qty = detail.getQuantity();
			
			//Descontat stock
			stockService.decreaseStock(
					saved.getStore().getId(), 
					variantId, 
					qty);
			
			//Registrar movimiento de inventario
			InventoryMovementRequest movement = new InventoryMovementRequest();
			movement.setProductVariantId(variantId);
			movement.setType(MovementType.OUT);
			movement.setQuantity(qty);
			movement.setReason("Sale ID: " +  saved.getId());
			movement.setUserId(saved.getUser().getId());
			
			movementService.create(movement);
		}
		return mapper.toDto(saved);
	}

	@Override
	@Transactional
	public Optional<SaleResponse> delete(Long id) {
		
		return saleRepository.findById(id).map(s -> {
			SaleResponse dto = mapper.toDto(s);
			saleRepository.delete(s);
			return dto;
			
		});
	}

}
