package com.erxss.sports_pos.dto.request;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SaleDetailRequest {

	private Long saleId;
	private Long productVariantId;
	private Integer quantity;
	private BigDecimal price;
}
