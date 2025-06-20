package com.erxss.sports_pos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erxss.sports_pos.entity.Brand;


public interface BrandRepository extends JpaRepository<Brand, Long> {
	Optional<Brand> findByName(String name);
	boolean existsByNameIgnoreCase(String name);

}
