package com.erxss.sports_pos.dto.request;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductRequest {
	private String name;
	private String description;
	private String barcode;
	private BigDecimal price;
	private BigDecimal cost;
	private Long categoryId;
	private Long brandId;
	private Long sportId;
}
