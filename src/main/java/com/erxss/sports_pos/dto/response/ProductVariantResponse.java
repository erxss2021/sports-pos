package com.erxss.sports_pos.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductVariantResponse {
	private Long id;
	private String productName;
	private String sizeLabel;	
	private String colorName;
	private String storeName; // nuevo campo
	private Integer stock;
	private Integer minStock;

}
