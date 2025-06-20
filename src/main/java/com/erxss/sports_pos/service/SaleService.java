package com.erxss.sports_pos.service;

import java.util.List;
import java.util.Optional;

import com.erxss.sports_pos.dto.request.SaleRequest;
import com.erxss.sports_pos.dto.response.SaleResponse;

public interface SaleService {

	List<SaleResponse> findAll();
	Optional<SaleResponse> findById(Long id);
	SaleResponse create(SaleRequest request);
	Optional<SaleResponse> delete(Long id);
}
