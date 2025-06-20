package com.erxss.sports_pos.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductVariantRequest {
	private Long productId;
	private Long storeId; // nuevo campo	
	private Long sizeId;
	private Long colorId;
	private Integer stock;
	private Integer minStock;

}
