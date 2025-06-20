package com.erxss.sports_pos.service;

import java.util.List;
import java.util.Optional;

import com.erxss.sports_pos.dto.request.PurchaseRequest;
import com.erxss.sports_pos.dto.response.PurchaseResponse;
import com.erxss.sports_pos.enums.PurcheseStatus;

public interface PurchaseService {

	List<PurchaseResponse> findAll();
	Optional<PurchaseResponse> findById(Long id);
	PurchaseResponse create(PurchaseRequest request);
	Optional<PurchaseResponse> updateStatus(Long id, PurcheseStatus status);
	Optional<PurchaseResponse> delete(Long id);
	
}
