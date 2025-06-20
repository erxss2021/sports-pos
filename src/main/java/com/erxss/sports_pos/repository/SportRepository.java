package com.erxss.sports_pos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erxss.sports_pos.entity.Sport;

public interface SportRepository extends JpaRepository<Sport, Long> {
	Optional<Sport> findByName(String name);
	boolean existsByNameIgnoreCase(String name);
}
