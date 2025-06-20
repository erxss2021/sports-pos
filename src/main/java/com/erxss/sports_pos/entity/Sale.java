package com.erxss.sports_pos.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.erxss.sports_pos.enums.PaymentMethod;
import com.erxss.sports_pos.enums.SaleStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sales")
@Data
@NoArgsConstructor
public class Sale {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDateTime saleDate;
	
	@ManyToOne(optional = false)
	private Customer customer;
	
	@ManyToOne(optional = false)
	private Store store;
	
	@ManyToOne(optional = false)
	private User user; //vendedor, cajero
	
	private BigDecimal totalAmount;
	
	@Enumerated(EnumType.STRING)
	private PaymentMethod paymentMethod;  //Enum
	
	@Enumerated(EnumType.STRING)
	private SaleStatus status; //Enum: COMPLETED, CANCELLED
	
	@OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
	private List<SaleDetail> details;
}
