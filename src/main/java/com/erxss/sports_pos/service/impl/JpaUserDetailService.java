package com.erxss.sports_pos.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erxss.sports_pos.entity.User;
import com.erxss.sports_pos.repository.UserRepository;



@Service
public class JpaUserDetailService implements UserDetailsService{
	
	@Autowired 
	private UserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> userOptional = userRepository.findByUsername(username);
		if(userOptional.isEmpty()) {
			throw new UsernameNotFoundException(
					String.format("Username %s no existe en el sistema", username));
		}
		
		User user = userOptional.orElseThrow();
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName()))
				.collect(Collectors.toList());
		
		return new org.springframework.security.core
				.userdetails.User(
						user.getUsername(),
						user.getPassword(),
						user.isEnabled(),
						true,
						true,
						true,
						authorities);
	}
	
}
