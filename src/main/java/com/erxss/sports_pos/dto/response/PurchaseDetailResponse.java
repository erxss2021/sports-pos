package com.erxss.sports_pos.dto.response;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PurchaseDetailResponse {

	private Long id;
	private Long purchaseId;
	private Long productVariantId;
	private String productVariantName;
	private String variantAttributes; //Ej: "Color: Red, Size: M"
	private Integer quantity;
	private BigDecimal cost;
	private BigDecimal subtotal;
}
