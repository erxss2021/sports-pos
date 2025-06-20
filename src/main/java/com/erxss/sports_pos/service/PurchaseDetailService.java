package com.erxss.sports_pos.service;

import java.util.List;
import java.util.Optional;

import com.erxss.sports_pos.dto.request.PurchaseDetailRequest;
import com.erxss.sports_pos.dto.response.PurchaseDetailResponse;

public interface PurchaseDetailService {
	List<PurchaseDetailResponse> findAll();
	Optional<PurchaseDetailResponse> findById(Long id);
	List<PurchaseDetailResponse> findByPurhase(Long purchaseId);
	PurchaseDetailResponse create(PurchaseDetailRequest request);
	Optional<PurchaseDetailResponse> delete(Long id);
}
