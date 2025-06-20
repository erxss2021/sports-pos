package com.erxss.sports_pos.mapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

import com.erxss.sports_pos.dto.request.SaleRequest;
import com.erxss.sports_pos.dto.response.SaleDetailResponse;
import com.erxss.sports_pos.dto.response.SaleResponse;
import com.erxss.sports_pos.entity.Sale;
import com.erxss.sports_pos.entity.SaleDetail;
import com.erxss.sports_pos.repository.CustomerRepository;
import com.erxss.sports_pos.repository.ProductVariantRepository;
import com.erxss.sports_pos.repository.StoreRepository;
import com.erxss.sports_pos.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SaleMapper {

	private final CustomerRepository customerRepository;
	private final StoreRepository storeRepository;
	private final UserRepository userRepository;
	private final ProductVariantRepository variantRepository;
	
	public Sale toEntity(SaleRequest dto) {
		Sale sale = new Sale();
		sale.setCustomer(customerRepository.findById(dto.getCustomerId()).orElseThrow());
		sale.setStore(storeRepository.findById(dto.getStoreId()).orElseThrow());
		sale.setUser(userRepository.findById(dto.getUserId()).orElseThrow());
		sale.setStatus(dto.getStatus());
		sale.setPaymentMethod(dto.getPaymentMethod());
		sale.setSaleDate(LocalDateTime.now());
		
		List<SaleDetail> details = dto.getDetails().stream().map(d -> {
			SaleDetail detail = new SaleDetail();
			detail.setProductVariant(variantRepository.findById(d.getProductVariantId()).orElseThrow());
			detail.setQuantity(d.getQuantity());
			detail.setPrice(d.getPrice());
			
			//Calcular subtotal
			BigDecimal subtotal = d.getPrice().multiply(BigDecimal.valueOf(d.getQuantity()));
			detail.setSubtotal(subtotal);
			
			detail.setSale(sale);
			return detail;
		}).toList();
		
		
		sale.setDetails(details);
		
		//Calcular totalAmount sumando subtotales
		BigDecimal total = details.stream()
				.map(SaleDetail::getSubtotal)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		sale.setTotalAmount(total);
		
		return sale;
	}
	
	public SaleResponse toDto(Sale sale) {
		SaleResponse dto = new SaleResponse();
		
		dto.setId(sale.getId());
		dto.setCustomerName(sale.getCustomer().getFullName());
		dto.setStoreName(sale.getStore().getName());
		dto.setUserEmail(sale.getUser().getEmail());
		dto.setTotalAmount(sale.getTotalAmount());
		dto.setPaymentMethod(sale.getPaymentMethod());
		dto.setSaleDate(sale.getSaleDate());
		dto.setStatus(sale.getStatus());
		dto.setDetails(sale.getDetails().stream().map(d -> {
			SaleDetailResponse detailDto = new SaleDetailResponse();
			detailDto.setProductVariantName(d.getProductVariant().getProduct().getName());
			detailDto.setQuantity(d.getQuantity());
			detailDto.setPrice(d.getPrice());
			return detailDto;
		}).toList());
		
		return dto;
	}
}
