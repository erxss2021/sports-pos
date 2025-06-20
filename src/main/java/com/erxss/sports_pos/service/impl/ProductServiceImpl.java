package com.erxss.sports_pos.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erxss.sports_pos.dto.request.ProductRequest;
import com.erxss.sports_pos.dto.response.ProductResponse;
import com.erxss.sports_pos.mapper.ProductMapper;
import com.erxss.sports_pos.repository.BrandRepository;
import com.erxss.sports_pos.repository.CategoryRepository;
import com.erxss.sports_pos.repository.ProductRepository;
import com.erxss.sports_pos.repository.SportRepository;
import com.erxss.sports_pos.service.ProductService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
	
	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;
	private final BrandRepository brandRepository;
	private final SportRepository sportRepository;
	private final ProductMapper mapper;

	@Override
	@Transactional(readOnly = true)
	public List<ProductResponse> findAll() {
		
		return productRepository.findAll().stream()
				.map(mapper::toDto).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<ProductResponse> findById(Long id) {
		
		return productRepository.findById(id).map(mapper::toDto);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ProductResponse> searchByName(String name) {
		
		return productRepository.findByNameContainingIgnoreCase(name)
				.stream()
				.map(mapper::toDto)
				.collect(Collectors.toList());
	}

	@Override
	@Transactional
	public ProductResponse create(ProductRequest request) {
		
		return mapper.toDto(productRepository.save(mapper.toEntity(request)));
	}

	@Override
	@Transactional
	public Optional<ProductResponse> update(Long id, ProductRequest request) {
		
		return productRepository.findById(id).map(existing -> {
			existing.setName(request.getName());
			existing.setDescription(request.getDescription());
			existing.setBarcode(request.getBarcode());
			existing.setPrice(request.getPrice());
			existing.setCost(request.getCost());
			
			existing.setCategory(categoryRepository.findById(request.getCategoryId())
					.orElseThrow(() -> new EntityNotFoundException("Category not found")));
			
			existing.setBrand(brandRepository.findById(request.getBrandId())
					.orElseThrow(() -> new EntityNotFoundException("Brand not found")));
			
			existing.setSport(sportRepository.findById(request.getSportId())
					.orElseThrow(() -> new EntityNotFoundException("Sport not found")));
			
			return mapper.toDto(productRepository.save(existing));
			
		});
	}

	@Override
	@Transactional
	public Optional<ProductResponse> delete(Long id) {
		
		return productRepository.findById(id).map(product -> {
			ProductResponse dto = mapper.toDto(product);
			productRepository.delete(product);
			return dto;
		});
	}

}
