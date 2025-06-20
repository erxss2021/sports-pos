package com.erxss.sports_pos.mapper;

import org.springframework.stereotype.Component;

import com.erxss.sports_pos.dto.request.StockRequest;
import com.erxss.sports_pos.dto.response.StockResponse;
import com.erxss.sports_pos.entity.Stock;
import com.erxss.sports_pos.repository.ProductVariantRepository;
import com.erxss.sports_pos.repository.StoreRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class StockMapper {

	private final StoreRepository storeRepository;
	private final ProductVariantRepository variantRepository;
	
	public Stock toEntity(StockRequest dto) {
		Stock stock = new Stock();
		stock.setStore(storeRepository.findById(dto.getStoreId()).orElseThrow());
		stock.setProductVariant(variantRepository.findById(dto.getProductVariantId()).orElseThrow());
		stock.setQuantity(dto.getQuantity());
		
		return stock;
	}
	
	public StockResponse toDto(Stock stock) {
		StockResponse dto = new StockResponse();
		dto.setId(stock.getId());
		dto.setStoreName(stock.getStore().getName());
		dto.setProductName(stock.getProductVariant().getProduct().getName());
		dto.setVariantAttributes("Color: " + stock.getProductVariant().getColor().getName() +
								", Size: " + stock.getProductVariant().getSize().getLabel());
		dto.setQuantity(stock.getQuantity());
		
		return dto;
	}
}

