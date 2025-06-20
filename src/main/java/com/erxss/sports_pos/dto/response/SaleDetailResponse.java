package com.erxss.sports_pos.dto.response;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SaleDetailResponse {

	private Long id;
	private Long saleId;
	private Long productVariantId;
	private String productVariantName;
	private Integer quantity;
	private BigDecimal price;
	private BigDecimal subtotal;
	
	
}
