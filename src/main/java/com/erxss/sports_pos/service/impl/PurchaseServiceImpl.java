package com.erxss.sports_pos.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//import org.apache.catalina.mapper.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erxss.sports_pos.dto.request.InventoryMovementRequest;
import com.erxss.sports_pos.dto.request.PurchaseRequest;
import com.erxss.sports_pos.dto.response.PurchaseResponse;
import com.erxss.sports_pos.entity.Purchase;
import com.erxss.sports_pos.entity.PurchaseDetail;
import com.erxss.sports_pos.enums.MovementType;
import com.erxss.sports_pos.enums.PurcheseStatus;
import com.erxss.sports_pos.mapper.PurchaseMapper;
import com.erxss.sports_pos.repository.PurchaseRepository;
import com.erxss.sports_pos.service.InventoryMovementService;
//import com.erxss.sports_pos.repository.SupplierRepository;
//import com.erxss.sports_pos.repository.UserRepository;
import com.erxss.sports_pos.service.PurchaseService;
import com.erxss.sports_pos.service.StockService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

	
	private final PurchaseRepository purchaseRepository;
	private final StockService stockService;
	private final InventoryMovementService movementService;
	//private final SupplierRepository supplierRepository;
	//private final UserRepository userRepository;
	private final PurchaseMapper mapper;
	
	@Override
	@Transactional(readOnly = true)
	public List<PurchaseResponse> findAll() {
		
		return purchaseRepository.findAll()
				.stream()
				.map(mapper::toDto)
				.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<PurchaseResponse> findById(Long id) {
		
		return purchaseRepository.findById(id).map(mapper::toDto);
	}

	@Override
	@Transactional
	public PurchaseResponse create(PurchaseRequest request) {
		Purchase purchase = mapper.toEntity(request);
		Purchase saved = purchaseRepository.save(purchase);
		
		for(PurchaseDetail detail: saved.getDetails()) {
			
			Long variantId = detail.getProductVariant().getId();
			Integer qty = detail.getQuantity();
			
			//Aumentar stock
			stockService.increaseStock(
					saved.getStore().getId(), 
					variantId, 
					qty);
			
			//Registrar movimiento de inventario
			InventoryMovementRequest movement = new InventoryMovementRequest();
			movement.setProductVariantId(variantId);
			movement.setType(MovementType.IN);
			movement.setQuantity(qty);
			movement.setReason("Purchase ID: " + saved.getId());
			movement.setUserId(saved.getUser().getId());
			
			movementService.create(movement);
		}
		return mapper.toDto(saved);
	}

	@Override
	@Transactional
	public Optional<PurchaseResponse> updateStatus(Long id, PurcheseStatus status) {
		
		return purchaseRepository.findById(id).map(purchase ->{
			purchase.setStatus(status);
			return mapper.toDto(purchaseRepository.save(purchase));
		});
	}

	@Override
	@Transactional
	public Optional<PurchaseResponse> delete(Long id) {
		
		return purchaseRepository.findById(id).map(purchase -> {
			PurchaseResponse dto = mapper.toDto(purchase);
			purchaseRepository.delete(purchase);
			return dto;
		});
	}

}
