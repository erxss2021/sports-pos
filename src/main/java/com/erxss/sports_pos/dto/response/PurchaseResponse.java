package com.erxss.sports_pos.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.erxss.sports_pos.enums.PurcheseStatus;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PurchaseResponse {

	private Long id;
	private LocalDateTime purchaseDate;
	private String userName;
	private String supplierName;
	private BigDecimal totalAmount;
	private PurcheseStatus status;
	private List<PurchaseDetailResponse> details;
}
