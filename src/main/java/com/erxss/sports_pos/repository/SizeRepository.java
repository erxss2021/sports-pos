package com.erxss.sports_pos.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erxss.sports_pos.entity.Size;

public interface SizeRepository extends JpaRepository<Size, Long> {
	Optional<Size> findByLabel(String label);
	boolean existsByLabelIgnoreCase(String label);
}
