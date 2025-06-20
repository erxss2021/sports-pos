package com.erxss.sports_pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erxss.sports_pos.entity.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {
	boolean existsByName(String name);
}
