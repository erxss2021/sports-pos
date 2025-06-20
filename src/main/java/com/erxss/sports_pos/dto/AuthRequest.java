package com.erxss.sports_pos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Petición de inicio de sesión")
public class AuthRequest {
	
	@Schema(example = "erxss")
	private String username;
	
	@Schema(example = "12345")
	private String password;

}
