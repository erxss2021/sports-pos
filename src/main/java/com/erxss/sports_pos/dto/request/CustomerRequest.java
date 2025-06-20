package com.erxss.sports_pos.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerRequest {

	private String fullName;
	private String email;
	private String phone;
	private String address;
	private String rfc;
}
