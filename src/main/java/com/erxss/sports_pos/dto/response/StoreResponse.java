package com.erxss.sports_pos.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StoreResponse {

	private Long id;
	private String name;
	private String address;
	private String email;
	private String phone;
}
