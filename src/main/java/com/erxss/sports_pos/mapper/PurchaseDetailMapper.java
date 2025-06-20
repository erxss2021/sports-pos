package com.erxss.sports_pos.mapper;


import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.erxss.sports_pos.dto.request.PurchaseDetailRequest;
import com.erxss.sports_pos.dto.response.PurchaseDetailResponse;
import com.erxss.sports_pos.entity.ProductVariant;
import com.erxss.sports_pos.entity.Purchase;
import com.erxss.sports_pos.entity.PurchaseDetail;
import com.erxss.sports_pos.repository.ProductVariantRepository;
import com.erxss.sports_pos.repository.PurchaseRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PurchaseDetailMapper {

	private final PurchaseRepository purchaseRepository;
	private final ProductVariantRepository  variantRepository;
	
	public PurchaseDetail toEntity(PurchaseDetailRequest dto) {
		//ProductVariant variant = variantRepository.findById(dto.getProductVariantId())
		//	.orElseThrow(()  -> new EntityNotFoundException("ProductVariant not found"));
		
		PurchaseDetail detail = new PurchaseDetail();
		
		detail.setPurchase(purchaseRepository.findById(dto.getPurchaseId())
				.orElseThrow(() -> new EntityNotFoundException("Purchase not found")));
		
		detail.setProductVariant(variantRepository.findById(dto.getProductVariantId())
				.orElseThrow(() -> new EntityNotFoundException("Product variant not found")));
		
		detail.setQuantity(dto.getQuantity());
		detail.setCost(dto.getCost());
		
		BigDecimal subtotal = dto.getCost().multiply(BigDecimal.valueOf(dto.getQuantity()));
		detail.setSubtotal(subtotal);
		return detail;
	}

	public PurchaseDetailResponse toDto(PurchaseDetail detail) {
		PurchaseDetailResponse dto = new PurchaseDetailResponse();
		dto.setId(detail.getId());
		dto.setPurchaseId(detail.getPurchase().getId());
		dto.setProductVariantId(detail.getProductVariant().getId());
		dto.setProductVariantName(detail.getProductVariant().getProduct().getName());
		String attributes = "Color: " + detail.getProductVariant().getColor().getName() +
							", Size: " + detail.getProductVariant().getSize().getLabel();
		
		dto.setVariantAttributes(attributes);
		dto.setQuantity(detail.getQuantity());
		dto.setCost(detail.getCost());
		dto.setSubtotal(detail.getSubtotal());
		return dto;
	}

}
