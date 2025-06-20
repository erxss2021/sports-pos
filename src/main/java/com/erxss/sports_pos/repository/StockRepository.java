package com.erxss.sports_pos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erxss.sports_pos.entity.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {
	Optional<Stock> findByStoreIdAndProductVariantId(Long storeId, Long productVariantId);
}
