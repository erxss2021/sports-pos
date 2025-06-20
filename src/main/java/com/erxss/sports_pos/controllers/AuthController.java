package com.erxss.sports_pos.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erxss.sports_pos.entity.User;
import com.erxss.sports_pos.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Operaciones de login y registro")
public class AuthController {
	
	@Autowired
	private UserService userService;

	@Operation(summary = "Registrar nuevo usuario")
	@PostMapping("/register")
	public  ResponseEntity<?> register(@RequestBody User user){
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
	}
	
	@Operation(summary = "Login de usuario y generar Token JWT")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        // Esta implementación es solo para que Swagger muestre la ruta,
        // la autenticación real la maneja Spring Security.
        return ResponseEntity.ok("Este endpoint es manejado por Spring Security");
    }
	
	@GetMapping("/me")
	public ResponseEntity<?> getProfile(){
		return ResponseEntity.status(HttpStatus.OK).body(userService.getCurrentUser());
	}
	
}
