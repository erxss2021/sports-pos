package com.erxss.sports_pos.dto.request;

import java.math.BigDecimal;

import com.erxss.sports_pos.enums.PaymentMethod;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentRequest {

	private Long saleId;
	private BigDecimal amount;
	private PaymentMethod method;
	private String transactionId;
}
