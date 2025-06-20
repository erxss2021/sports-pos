package com.erxss.sports_pos.mapper;

import org.springframework.stereotype.Component;

import com.erxss.sports_pos.dto.request.CategoryRequest;
import com.erxss.sports_pos.dto.response.CategoryResponse;
import com.erxss.sports_pos.entity.Category;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CategoryMapper {

	public Category toEntity(CategoryRequest dto) {
		Category c = new Category();
		c.setName(dto.getName());
		c.setDescription(dto.getDescription());
		return c;
	}
	
	public CategoryResponse toDto(Category category) {
		CategoryResponse dto = new CategoryResponse();
		dto.setId(category.getId());
		dto.setName(category.getName());
		dto.setDescription(category.getDescription());
		return dto;
	}
}
