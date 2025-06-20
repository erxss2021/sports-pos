package com.erxss.sports_pos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erxss.sports_pos.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

	Optional<Supplier> findByTaxId(String taxId);
	boolean existsByEmail(String email);
}
