package com.erxss.sports_pos.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SupplierResponse {

	private Long id;
	private String name;
	private String email;
	private String phone;
	private String address;
	private String taxId;
}
