package com.erxss.sports_pos.service;

import java.util.List;
import java.util.Optional;

import com.erxss.sports_pos.dto.UserDto;
import com.erxss.sports_pos.entity.User;





public interface UserService {
	List<User> findAll();
	
	User save(User user); 
	
	User getCurrentUser();
	
	Optional<User> findById(Long id);
	
	Optional<User> update(Long id, UserDto user);
	
	Optional<User> delete(Long id);
}
