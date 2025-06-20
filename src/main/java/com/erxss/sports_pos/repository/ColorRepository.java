package com.erxss.sports_pos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.erxss.sports_pos.entity.Color;

public interface ColorRepository extends JpaRepository<Color, Long> {
	Optional<Color> findByName(String name);
	boolean existsByNameIgnoreCase(String name);
}
