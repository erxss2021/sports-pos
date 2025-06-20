package com.erxss.sports_pos.mapper;

import org.springframework.stereotype.Component;

import com.erxss.sports_pos.dto.request.BrandRequest;
import com.erxss.sports_pos.dto.response.BrandResponse;
import com.erxss.sports_pos.entity.Brand;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BrandMapper {

	public Brand toEntity(BrandRequest dto) {
		Brand b = new Brand();
		b.setName(dto.getName());
		b.setDescription(dto.getDescription());
		return b;
	}
	
	public BrandResponse toDto(Brand brand) {
		BrandResponse dto = new BrandResponse();
		dto.setId(brand.getId());
		dto.setName(brand.getName());
		dto.setDescription(brand.getDescription());
		return dto;
	}
}
