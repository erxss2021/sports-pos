package com.erxss.sports_pos.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erxss.sports_pos.dto.request.CategoryRequest;
import com.erxss.sports_pos.dto.response.CategoryResponse;
import com.erxss.sports_pos.mapper.CategoryMapper;
import com.erxss.sports_pos.repository.CategoryRepository;
import com.erxss.sports_pos.service.CategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
	
	private final CategoryRepository categoryRepository;
	private final CategoryMapper mapper;

	@Override
	@Transactional(readOnly = true)
	public List<CategoryResponse> findAll() {
		
		return categoryRepository.findAll()
				.stream()
				.map(mapper::toDto)
				.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<CategoryResponse> findById(Long id) {
		
		return categoryRepository.findById(id).map(mapper::toDto);
	}

	@Override
	@Transactional
	public CategoryResponse create(CategoryRequest request) {
		if(categoryRepository
				.existsByNameIgnoreCase(request.getName())) {
			throw new 
			IllegalArgumentException("Category already exists");
		}
		return mapper.toDto(categoryRepository
				.save(mapper.toEntity(request)));
	}

	@Override
	@Transactional
	public Optional<CategoryResponse> update(Long id, CategoryRequest request) {
		
		return categoryRepository.findById(id).map(c ->{
			c.setName(request.getName());
			c.setDescription(request.getDescription());
			return mapper.toDto(categoryRepository.save(c));
		});
	}

	@Override
	@Transactional
	public Optional<CategoryResponse> delete(Long id) {
		
		return categoryRepository.findById(id).map(c -> {
			CategoryResponse dto = mapper.toDto(c);
			categoryRepository.delete(c);
			return dto;
		});
	}

}
