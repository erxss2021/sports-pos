package com.erxss.sports_pos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erxss.sports_pos.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByUsername(String username);
	boolean existsByUsername(String username);
	List<User> findByRoles_Name(String role);
}
