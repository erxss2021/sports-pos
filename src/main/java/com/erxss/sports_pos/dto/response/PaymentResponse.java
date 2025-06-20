package com.erxss.sports_pos.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.erxss.sports_pos.enums.PaymentMethod;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentResponse {

	private Long id;
	private long saleId;
	private LocalDateTime paymentDate;
	private BigDecimal amout;
	private PaymentMethod method;
	private String transactionId;
}
