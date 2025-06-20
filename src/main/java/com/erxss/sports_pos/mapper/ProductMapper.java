package com.erxss.sports_pos.mapper;

import org.springframework.stereotype.Component;

import com.erxss.sports_pos.dto.request.ProductRequest;
import com.erxss.sports_pos.dto.response.ProductResponse;
import com.erxss.sports_pos.entity.Product;
import com.erxss.sports_pos.repository.BrandRepository;
import com.erxss.sports_pos.repository.CategoryRepository;
import com.erxss.sports_pos.repository.SportRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductMapper {

	private final CategoryRepository categoryRepository;
	private final BrandRepository brandRepository;
	private final SportRepository sportRepository;
	
	public Product toEntity(ProductRequest dto) {
		Product product = new Product();
		
		product.setName(dto.getName());
		product.setDescription(dto.getDescription());
		product.setBarcode(dto.getBarcode());
		product.setPrice(dto.getPrice());
		product.setCost(dto.getCost());
		
		product.setCategory(categoryRepository.findById(dto.getCategoryId())
				.orElseThrow(() -> new EntityNotFoundException("Category not found")));
		
		product.setBrand(brandRepository.findById(dto.getBrandId())
				.orElseThrow(() -> new EntityNotFoundException("Brand not found")));
		
		product.setSport(sportRepository.findById(dto.getSportId())
				.orElseThrow(() -> new EntityNotFoundException("Sport not found")));
		
		return product;
	}
	
	public ProductResponse toDto(Product entity) {
		ProductResponse dto = new ProductResponse();
		
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setDescription(entity.getDescription());
		dto.setBarcode(entity.getBarcode());
		dto.setPrice(entity.getPrice());
		dto.setCost(entity.getCost());
		dto.setCategoryName(entity.getCategory().getName());
		dto.setBrandName(entity.getBrand().getName());
		dto.setSportName(entity.getSport().getName());
		
		return dto;
	}
}
