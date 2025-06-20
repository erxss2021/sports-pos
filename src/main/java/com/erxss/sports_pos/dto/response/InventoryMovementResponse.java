package com.erxss.sports_pos.dto.response;

import java.time.LocalDateTime;

import com.erxss.sports_pos.enums.MovementType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InventoryMovementResponse {

	private Long id;
	private String productName;
	private String variantAttributes;
	private MovementType type;
	private Integer quantity;
	private String reason;
	private LocalDateTime date;
	private String userName;
}
