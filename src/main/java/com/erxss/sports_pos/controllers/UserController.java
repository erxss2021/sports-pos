package com.erxss.sports_pos.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erxss.sports_pos.dto.UserDto;
import com.erxss.sports_pos.entity.User;
import com.erxss.sports_pos.service.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuarios", description = "Operaciones con usuarios")
@PreAuthorize("hasRole('ADMIN')")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping
	public List<User> listar(){
		return userService.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getDetailsUser(@PathVariable Long id){
		Optional<User> optionalUser = userService.findById(id);
		if(optionalUser.isPresent()) {
			return ResponseEntity.ok(optionalUser.orElseThrow());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDto user){
		 System.out.println(">> JSON recibido:");
		    System.out.println("Username: " + user.getUsername());
		    System.out.println("Email: " + user.getEmail());
		    System.out.println("Password: " + user.getPassword());
		Optional<User> optionalUser = userService.update(id, user);
		if(optionalUser.isPresent())
			return ResponseEntity.status(HttpStatus.CREATED).body(optionalUser.get());
		
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id){
		Optional<User> optionalUser = userService.delete(id);
		
		if(optionalUser.isPresent())
			return ResponseEntity.ok(optionalUser.get());
		
		return ResponseEntity.notFound().build();
	}
	
}
