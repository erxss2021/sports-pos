package com.erxss.sports_pos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erxss.sports_pos.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findByNameContainingIgnoreCase(String name);
}
