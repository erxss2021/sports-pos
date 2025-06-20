package com.erxss.sports_pos.mapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.erxss.sports_pos.dto.request.PurchaseRequest;
import com.erxss.sports_pos.dto.response.PurchaseResponse;
import com.erxss.sports_pos.entity.Purchase;
import com.erxss.sports_pos.entity.PurchaseDetail;
import com.erxss.sports_pos.repository.StoreRepository;
import com.erxss.sports_pos.repository.SupplierRepository;
import com.erxss.sports_pos.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PurchaseMapper {

	private final SupplierRepository supplierRepository;
	private final UserRepository userRepository;
	private final StoreRepository storeRepository;
	private final PurchaseDetailMapper detailMapper;
	
	public Purchase toEntity(PurchaseRequest dto) {
		Purchase p = new Purchase();
		p.setPurchaseDate(LocalDateTime.now());
		p.setSupplier(supplierRepository.findById(dto.getSupplierId()).orElseThrow());
		p.setUser(userRepository.findById(dto.getUserId()).orElseThrow());
		p.setStore(storeRepository.findById(dto.getStoreId()).orElseThrow());
		p.setTotalAmount(dto.getTotalAmount());
		p.setStatus(dto.getStatus());
		
		List<PurchaseDetail> details = dto.getDetails().stream()
				.map(d -> detailMapper.toEntity(d))
				.collect(Collectors.toList());
		
		p.setDetails(details);
		
		return p;
	}
	
	public PurchaseResponse toDto(Purchase purchase) {
		PurchaseResponse dto = new PurchaseResponse();
		dto.setId(purchase.getId());
		dto.setPurchaseDate(purchase.getPurchaseDate());
		dto.setSupplierName(purchase.getSupplier().getName());
		dto.setUserName(purchase.getUser().getUsername());
		dto.setTotalAmount(purchase.getTotalAmount());
		dto.setStatus(purchase.getStatus());
		
		dto.setDetails(purchase.getDetails().stream()
				.map(detailMapper::toDto)
				.collect(Collectors.toList()));
		return dto;
	}
}
