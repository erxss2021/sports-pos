package com.erxss.sports_pos.service;

import java.util.List;
import java.util.Optional;

import com.erxss.sports_pos.dto.request.SportRequest;
import com.erxss.sports_pos.dto.response.SportResponse;

public interface SportService {
	
	List<SportResponse> findAll();
	Optional<SportResponse> findById(Long id);
	SportResponse create(SportRequest request);
	Optional<SportResponse> update(Long id, SportRequest request);
	Optional<SportResponse> delete(Long id);

}
