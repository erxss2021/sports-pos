package com.erxss.sports_pos.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StockRequest {

	private Long storeId;
	private Long productVariantId;
	private Integer quantity;
}
