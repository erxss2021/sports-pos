package com.erxss.sports_pos.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StockResponse {

	private Long id;
	private String storeName;
	private String productName;
	private String variantAttributes;
	private Integer quantity;
}
