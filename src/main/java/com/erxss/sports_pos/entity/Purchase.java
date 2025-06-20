package com.erxss.sports_pos.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.erxss.sports_pos.enums.PurcheseStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "purchases")
@Data
@NoArgsConstructor
public class Purchase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDateTime purchaseDate;
	
	@ManyToOne
	private Supplier supplier; 
	
	@ManyToOne
	private User user; //quien registro la compra
	
	private BigDecimal totalAmount;
	
	@Enumerated(EnumType.STRING)
	private PurcheseStatus status;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "store_id")
	private Store store;
	
	@OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PurchaseDetail> details;
}
