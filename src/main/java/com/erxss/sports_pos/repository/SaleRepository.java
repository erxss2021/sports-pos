package com.erxss.sports_pos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erxss.sports_pos.entity.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {

	List<Sale> findByCustomerId(Long customerId);
	List<Sale> findByUserId(Long userId);
}
