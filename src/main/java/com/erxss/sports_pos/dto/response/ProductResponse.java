package com.erxss.sports_pos.dto.response;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductResponse {
	private Long id;
	private String name;
	private String description;
	private String barcode;
	private BigDecimal price;
	private BigDecimal cost;
	private String categoryName;
	private String brandName;
	private String sportName;
}
