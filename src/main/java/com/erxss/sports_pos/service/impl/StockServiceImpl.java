package com.erxss.sports_pos.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erxss.sports_pos.dto.request.StockRequest;
import com.erxss.sports_pos.dto.response.StockResponse;
import com.erxss.sports_pos.entity.Stock;
import com.erxss.sports_pos.mapper.StockMapper;
//import com.erxss.sports_pos.repository.ProductVariantRepository;
import com.erxss.sports_pos.repository.StockRepository;
//import com.erxss.sports_pos.repository.StoreRepository;
import com.erxss.sports_pos.service.StockService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

	
	private final StockRepository stockRepository;
	//private final StoreRepository storeRepository;
	//private final ProductVariantRepository variantRepository;
	private final StockMapper mapper;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<StockResponse> findAll() {
		
		return stockRepository.findAll()
				.stream()
				.map(mapper::toDto)
				.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<StockResponse> findById(Long id) {
		
		return stockRepository.findById(id).map(mapper::toDto);
	}

	@Override
	@Transactional
	public StockResponse addOrUpdateStock(StockRequest request) {
		 if (request.getStoreId() == null || request.getProductVariantId() == null) {
		        throw new IllegalArgumentException("Store ID and ProductVariant ID must not be null");
		    }
		Stock stock = stockRepository.findByStoreIdAndProductVariantId(request.getStoreId(), request.getProductVariantId())
				.map(s -> {
					s.setQuantity(request.getQuantity());
					return s;
				})
				.orElseGet(() -> mapper.toEntity(request));
		return mapper.toDto(stockRepository.save(stock));
	}

	@Override
	@Transactional
	public void increaseStock(Long storeId, Long variantId, Integer quantity) {
		 if (storeId == null || variantId == null) {
		        throw new IllegalArgumentException("Store ID and ProductVariant ID must not be null");
		    }
		Stock stock = stockRepository.findByStoreIdAndProductVariantId(storeId, variantId)
				.orElseThrow(() -> new EntityNotFoundException("Stock not found"));
		stock.setQuantity(stock.getQuantity()  + quantity);
		stockRepository.save(stock);

	}

	@Override
	@Transactional
	public void decreaseStock(Long storeId, Long variantId, Integer quantity) {
		if (storeId == null || variantId == null) {
	        throw new IllegalArgumentException("Store ID and ProductVariant ID must not be null");
	    }
		Stock stock = stockRepository.findByStoreIdAndProductVariantId(storeId, variantId)
				.orElseThrow(() -> new EntityNotFoundException("Stock not found"));
		
		if(stock.getQuantity() < quantity)
			throw new IllegalStateException("Insufficient stock");
		
		stock.setQuantity(stock.getQuantity() - quantity);
		stockRepository.save(stock);

	}

}
