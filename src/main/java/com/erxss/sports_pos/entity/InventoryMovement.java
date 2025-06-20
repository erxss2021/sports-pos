package com.erxss.sports_pos.entity;

import java.time.LocalDateTime;

import com.erxss.sports_pos.enums.MovementType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "inventory_movements")
@Data
@NoArgsConstructor
public class InventoryMovement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(optional = false)
	private ProductVariant productVariant;
	
	@Enumerated(EnumType.STRING)
	private MovementType type;
	
	@Column(nullable = false)
	private Integer quantity;
	private String reason;
	
	private LocalDateTime date;
	
	@ManyToOne(optional = false)
	private User user;

}
