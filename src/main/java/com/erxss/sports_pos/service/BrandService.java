package com.erxss.sports_pos.service;

import java.util.List;
import java.util.Optional;

import com.erxss.sports_pos.dto.request.BrandRequest;
import com.erxss.sports_pos.dto.response.BrandResponse;

public interface BrandService {
	List<BrandResponse> findAll();
	Optional<BrandResponse> findById(Long id);
	BrandResponse create(BrandRequest request);
	Optional<BrandResponse> update(Long id, BrandRequest request);
	Optional<BrandResponse> delete(Long id);
}
