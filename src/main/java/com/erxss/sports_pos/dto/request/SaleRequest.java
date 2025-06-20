package com.erxss.sports_pos.dto.request;


import java.util.List;

import com.erxss.sports_pos.enums.PaymentMethod;
import com.erxss.sports_pos.enums.SaleStatus;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SaleRequest {

	private Long customerId;
	private Long storeId;
	private Long userId;
	//private BigDecimal totalAmount;
	private PaymentMethod paymentMethod;
	private SaleStatus status;
	private List<SaleDetailRequest> details;
}
