	package com.erxss.sports_pos.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erxss.sports_pos.dto.UserDto;
import com.erxss.sports_pos.entity.Role;
import com.erxss.sports_pos.entity.User;
import com.erxss.sports_pos.repository.RoleRepository;
import com.erxss.sports_pos.repository.UserRepository;
import com.erxss.sports_pos.service.UserService;



@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	@Transactional(readOnly = true)
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<User> findById(Long id) {
		
		return userRepository.findById(id);
	}
	
	@Override
	@Transactional
	public User save(User user) {
		List<Role> roles = new ArrayList<>();
		
		if(user.getRoles().size() > 0) {
			Optional<Role> roleOptional = roleRepository.findByName(user.getRoles().get(0).getName());
			roleOptional.ifPresent(roles::add);
		}
		if(user.isAdmin()) {
			Optional<Role> roleOptionalAdmin = roleRepository.findByName("ROLE_ADMIN");
			roleOptionalAdmin.ifPresent(roles::add);
		}
		
		user.setRoles(roles);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public User getCurrentUser() {
		Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		return userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario no autenticado."));
		
	}

	

	@Override
	@Transactional
	public Optional<User> update(Long id, UserDto user) {
		Optional<User> optionalUser = userRepository.findById(id);
		if(optionalUser.isPresent()) {
			User userDB = optionalUser.orElseThrow();
			// Actualiza solo los campos permitidos
	        if (user.getEmail() != null) {
	            userDB.setEmail(user.getEmail());
	        }

	        if (user.getUsername() != null) {
	            userDB.setUsername(user.getUsername());
	        }

	        // Opcionalmente actualizar contraseña si viene una nueva
	        if (user.getPassword() != null && !user.getPassword().isBlank()) {
	            userDB.setPassword(passwordEncoder.encode(user.getPassword()));
	        }

	        // Evita sobrescribir a null. Solo actualiza si es explícitamente true/false
	        userDB.setEnabled(user.isEnabled());
			
		
			return Optional.of(userRepository.save(userDB));
		}
		return optionalUser;
	}

	@Override
	@Transactional
	public Optional<User> delete(Long id) {
		
		Optional<User> optionalUser = userRepository.findById(id);
		optionalUser.ifPresent(userRepository::delete);
		return optionalUser;
	}
	
	

}
