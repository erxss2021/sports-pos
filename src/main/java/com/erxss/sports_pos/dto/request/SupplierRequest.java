package com.erxss.sports_pos.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SupplierRequest {

	private String name;
	private String email;
	private String phone;
	private String address;
	private String taxId;
}
