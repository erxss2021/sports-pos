package com.erxss.sports_pos.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerResponse {

	private Long id;
	private String fullName;
	private String email;
	private String phone;
	private String address;
	private String rfc;
}
