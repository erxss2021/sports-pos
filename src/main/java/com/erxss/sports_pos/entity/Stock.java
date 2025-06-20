package com.erxss.sports_pos.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "stock",
		uniqueConstraints = @UniqueConstraint(columnNames = {"store_id", "product_variant_id"}))
@Data
@NoArgsConstructor
public class Stock {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "store_id")
	private Store store; 
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "product_variant_id")
	private ProductVariant productVariant;
	
	@Column(nullable = false)
	private Integer quantity;
	

}
