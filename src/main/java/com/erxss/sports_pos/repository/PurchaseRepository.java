package com.erxss.sports_pos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erxss.sports_pos.entity.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

	List<Purchase> findBySupplierId(Long supplierId);
}
