package com.erxss.sports_pos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erxss.sports_pos.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(String name);
}
