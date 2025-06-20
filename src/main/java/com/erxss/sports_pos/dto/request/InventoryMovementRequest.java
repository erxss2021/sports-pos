package com.erxss.sports_pos.dto.request;

import com.erxss.sports_pos.enums.MovementType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InventoryMovementRequest {

	private Long productVariantId;
	private MovementType type;
	private Integer quantity;
	private String reason;
	private Long userId;
}
