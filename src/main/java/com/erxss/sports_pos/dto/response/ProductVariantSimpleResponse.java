package com.erxss.sports_pos.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductVariantSimpleResponse {
	private Long id;
	private String storeName; // nuevo campo		
    private String productName;
    private String sizeName;
    private String colorName;
    private Integer stock;
	private Integer minStock;		
}
