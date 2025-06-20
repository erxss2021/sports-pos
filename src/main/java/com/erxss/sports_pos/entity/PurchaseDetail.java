package com.erxss.sports_pos.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "purchase_details")
@Data
@NoArgsConstructor
public class PurchaseDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Purchase purchase;
	
	@ManyToOne
	private ProductVariant productVariant;
	
	private Integer quantity;
	private BigDecimal cost;
	private BigDecimal subtotal;
}
