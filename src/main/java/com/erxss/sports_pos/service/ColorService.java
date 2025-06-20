package com.erxss.sports_pos.service;

import java.util.List;
import java.util.Optional;

import com.erxss.sports_pos.dto.request.ColorRequest;
import com.erxss.sports_pos.dto.response.ColorResponse;

public interface ColorService {

	List<ColorResponse> findAll();
	Optional<ColorResponse> findById(Long id);
	ColorResponse create(ColorRequest request);
	Optional<ColorResponse> update(Long id, ColorRequest request);
	Optional<ColorResponse> delete(Long id);
}
