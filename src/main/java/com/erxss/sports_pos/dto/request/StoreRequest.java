package com.erxss.sports_pos.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StoreRequest {

	private String name;
	private String address;
	private String email;
	private String phone; 
}
