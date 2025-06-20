package com.erxss.sports_pos.repository;

import java.util.List;
import java.util.Optional;

import com.erxss.sports_pos.dto.request.SaleDetailRequest;
import com.erxss.sports_pos.dto.response.SaleDetailResponse;

public interface SaleDetailService {

	List<SaleDetailResponse> findAll();
	Optional<SaleDetailResponse> findById(Long id);
	List<SaleDetailResponse> findbySale(Long saleId);
	
	SaleDetailResponse create(SaleDetailRequest request);
	Optional<SaleDetailResponse> delete(Long id);
}
