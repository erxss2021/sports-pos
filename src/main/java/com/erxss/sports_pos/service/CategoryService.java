package com.erxss.sports_pos.service;

import java.util.List;
import java.util.Optional;

import com.erxss.sports_pos.dto.request.CategoryRequest;
import com.erxss.sports_pos.dto.response.CategoryResponse;

public interface CategoryService {

	List<CategoryResponse> findAll();
	Optional<CategoryResponse> findById(Long id);
	CategoryResponse create(CategoryRequest request);
	Optional<CategoryResponse> update(Long id, CategoryRequest request);
	Optional<CategoryResponse> delete(Long id);
}
