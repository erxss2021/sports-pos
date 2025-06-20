package com.erxss.sports_pos.service;

import java.util.List;
import java.util.Optional;

import com.erxss.sports_pos.dto.request.SizeRequest;
import com.erxss.sports_pos.dto.response.SizeResponse;

public interface SizeService {

	List<SizeResponse> findAll();
	Optional<SizeResponse> findById(Long id);
	SizeResponse create(SizeRequest request);
	Optional<SizeResponse> update(Long id, SizeRequest request);
	Optional<SizeResponse> delete(Long id);
	
}
