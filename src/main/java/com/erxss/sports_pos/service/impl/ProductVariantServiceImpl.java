package com.erxss.sports_pos.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erxss.sports_pos.dto.request.ProductVariantRequest;
import com.erxss.sports_pos.dto.response.ProductVariantResponse;
import com.erxss.sports_pos.entity.ProductVariant;
import com.erxss.sports_pos.entity.Stock;
import com.erxss.sports_pos.entity.Store;
import com.erxss.sports_pos.mapper.ProductVariantMapper;
import com.erxss.sports_pos.repository.ProductVariantRepository;
import com.erxss.sports_pos.repository.StockRepository;
import com.erxss.sports_pos.repository.StoreRepository;
import com.erxss.sports_pos.service.ProductVariantService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductVariantServiceImpl implements ProductVariantService {

	private final ProductVariantRepository productVariantRepository;
	private final StockRepository stockRepository;
    private final StoreRepository storeRepository;
	private final ProductVariantMapper mapper;
	
	@Override
	@Transactional
	public ProductVariantResponse create(ProductVariantRequest request) {
		 // Convertimos el DTO a entidad
        ProductVariant variant = mapper.toEntity(request);

        // Obtenemos la tienda
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new EntityNotFoundException("Store not found"));

        variant.setStore(store);

        // Guardamos la variante
        variant = productVariantRepository.save(variant);

        // Buscar stock existente
        Optional<Stock> optionalStock = stockRepository.findByStoreIdAndProductVariantId(store.getId(), variant.getId());

        if (optionalStock.isPresent()) {
            Stock existingStock = optionalStock.get();
            existingStock.setQuantity(request.getStock());
            stockRepository.save(existingStock);
        } else {
            Stock newStock = new Stock();
            newStock.setStore(store);
            newStock.setProductVariant(variant);
            newStock.setQuantity(request.getStock());
            stockRepository.save(newStock);
        }


		return mapper.toDto(variant);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ProductVariantResponse> findByProductId(Long productId) {
		return productVariantRepository.findByProductId(productId)
				.stream()
				.map(mapper::toDto)
				.collect(Collectors.toList());
	}

	@Override
	@Transactional
	public Optional<ProductVariantResponse> delete(Long id) {
		return productVariantRepository.findById(id)
				.map(variant -> {
					ProductVariantResponse dto = mapper.toDto(variant);
					productVariantRepository.delete(variant);
					return dto;
				});
	}

}
