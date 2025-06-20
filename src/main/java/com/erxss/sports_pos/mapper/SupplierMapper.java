package com.erxss.sports_pos.mapper;

import org.springframework.stereotype.Component;

import com.erxss.sports_pos.dto.request.SupplierRequest;
import com.erxss.sports_pos.dto.response.SupplierResponse;
import com.erxss.sports_pos.entity.Supplier;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SupplierMapper {

	public Supplier toEntity(SupplierRequest dto) {
		Supplier s = new Supplier();
		s.setName(dto.getName());
		s.setEmail(dto.getEmail());
		s.setPhone(dto.getPhone());
		s.setAddress(dto.getAddress());
		s.setTaxId(dto.getTaxId());
		return s;
	}
	
	public SupplierResponse toDto(Supplier supplier) {
		SupplierResponse dto = new SupplierResponse();
		dto.setId(supplier.getId());
		dto.setName(supplier.getName());
		dto.setEmail(supplier.getEmail());
		dto.setPhone(supplier.getPhone());
		dto.setAddress(supplier.getAddress());
		dto.setTaxId(supplier.getTaxId());
		return dto;
	}
}
