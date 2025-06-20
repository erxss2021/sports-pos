package com.erxss.sports_pos.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.erxss.sports_pos.enums.PaymentMethod;
import com.erxss.sports_pos.enums.SaleStatus;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SaleResponse {

	private Long id;
	private String customerName;
	private String storeName;
	private String userEmail;
	private BigDecimal totalAmount;
	private PaymentMethod paymentMethod;
	private SaleStatus status;
	private LocalDateTime saleDate;
	private List<SaleDetailResponse> details;
}
