package com.erxss.sports_pos.dto.request;

import java.math.BigDecimal;
import java.util.List;

import com.erxss.sports_pos.enums.PurcheseStatus;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PurchaseRequest {

	private Long supplierId;
	private Long userId;
	private BigDecimal totalAmount;
	private PurcheseStatus status;
	private Long storeId;
	private List<PurchaseDetailRequest> details;
}
