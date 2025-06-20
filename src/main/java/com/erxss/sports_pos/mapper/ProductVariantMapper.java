package com.erxss.sports_pos.mapper;

import org.springframework.stereotype.Component;

import com.erxss.sports_pos.dto.request.ProductVariantRequest;
import com.erxss.sports_pos.dto.response.ProductVariantResponse;
import com.erxss.sports_pos.entity.ProductVariant;
import com.erxss.sports_pos.repository.ColorRepository;
import com.erxss.sports_pos.repository.ProductRepository;
import com.erxss.sports_pos.repository.SizeRepository;
import com.erxss.sports_pos.repository.StoreRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductVariantMapper {

	private final ProductRepository productRepository;
	private final SizeRepository sizeRepository;
	private final ColorRepository colorRepository;
	private final StoreRepository storeRepository;
	
	public ProductVariant toEntity(ProductVariantRequest dto) {
		ProductVariant variant = new ProductVariant();
		variant.setProduct(productRepository.findById(dto.getProductId())
				.orElseThrow(() -> new EntityNotFoundException("Product not found")));
		variant.setSize(sizeRepository.findById(dto.getSizeId())	
				.orElseThrow(() -> new EntityNotFoundException("Size not found")));
		variant.setColor(colorRepository.findById(dto.getColorId())
				.orElseThrow(() -> new EntityNotFoundException("Color not found")));
		
		variant.setStore(storeRepository.findById(dto.getStoreId()).orElseThrow()); // nuevo
		variant.setStock(dto.getStock());
		variant.setMinStock(dto.getMinStock());
		
		return variant;
	}
	
	public ProductVariantResponse toDto(ProductVariant variant) {
		ProductVariantResponse dto = new ProductVariantResponse();
		dto.setId(variant.getId());
		dto.setProductName(variant.getProduct().getName());
		dto.setSizeLabel(variant.getSize().getLabel());
		dto.setColorName(variant.getColor().getName());
		dto.setStoreName(variant.getStore().getName()); // nuevo

		dto.setStock(variant.getStock());
		dto.setMinStock(variant.getMinStock());
		return dto;
	}
	
}
