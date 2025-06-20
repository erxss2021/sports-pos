package com.erxss.sports_pos.mapper;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.erxss.sports_pos.dto.request.SaleDetailRequest;
import com.erxss.sports_pos.dto.response.SaleDetailResponse;
import com.erxss.sports_pos.entity.SaleDetail;
import com.erxss.sports_pos.repository.ProductVariantRepository;
import com.erxss.sports_pos.repository.SaleRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SaleDetailMapper {
	
	private final SaleRepository saleRepository;
	private final ProductVariantRepository variantRepository;
	
	public SaleDetail toEntity(SaleDetailRequest dto) {
		SaleDetail detail = new SaleDetail();
		detail.setSale(saleRepository.findById(dto.getSaleId())
				.orElseThrow(() -> new EntityNotFoundException("Sale not found")));
		
		detail.setProductVariant(variantRepository.findById(dto.getProductVariantId())
				.orElseThrow(() -> new EntityNotFoundException("Product variant not found")));
		
		detail.setQuantity(dto.getQuantity());
		detail.setPrice(dto.getPrice());
		detail.setSubtotal(dto.getPrice().multiply(BigDecimal.valueOf(dto.getQuantity())));
		
		return detail;
	}
	
	public SaleDetailResponse toDto(SaleDetail detail) {
		SaleDetailResponse dto = new SaleDetailResponse();
		
		dto.setId(detail.getId());
		dto.setSaleId(detail.getSale().getId());
		dto.setProductVariantId(detail.getProductVariant().getId());
		dto.setProductVariantName(detail.getProductVariant().getProduct().getName());
		dto.setQuantity(detail.getQuantity());
		dto.setPrice(detail.getPrice());
		dto.setSubtotal(detail.getSubtotal());
		
		return dto;
	}

}
