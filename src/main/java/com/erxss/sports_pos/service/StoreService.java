package com.erxss.sports_pos.service;

import java.util.List;
import java.util.Optional;

import com.erxss.sports_pos.dto.request.StoreRequest;
import com.erxss.sports_pos.dto.response.StoreResponse;

public interface StoreService {

	List<StoreResponse> findAll();
	Optional<StoreResponse> findById(Long id);
	StoreResponse create(StoreRequest request);
	Optional<StoreResponse> update(Long id, StoreRequest request);
	Optional<StoreResponse> delete(Long id);
}
