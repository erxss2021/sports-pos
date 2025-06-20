package com.erxss.sports_pos.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erxss.sports_pos.dto.request.ColorRequest;
import com.erxss.sports_pos.dto.response.ColorResponse;
import com.erxss.sports_pos.mapper.ColorMapper;
import com.erxss.sports_pos.repository.ColorRepository;
import com.erxss.sports_pos.service.ColorService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService {

	private final ColorRepository colorRepository;
	private final ColorMapper mapper;
	
	@Override
	@Transactional(readOnly = true)
	public List<ColorResponse> findAll() {
		
		return colorRepository.findAll()
				.stream()
				.map(mapper::toDto)
				.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<ColorResponse> findById(Long id) {
		
		return colorRepository.findById(id).map(mapper::toDto);
	}

	@Override
	@Transactional
	public ColorResponse create(ColorRequest request) {
		if(colorRepository.existsByNameIgnoreCase(request.getName())) {
			throw new IllegalArgumentException("Color already exists");
		}
		return mapper.toDto(colorRepository.save(mapper.toEntity(request)));
	}

	@Override
	@Transactional
	public Optional<ColorResponse> update(Long id, ColorRequest request) {
		
		return colorRepository.findById(id).map(c -> {
			c.setName(request.getName());
			c.setHexCode(request.getHexCode());
			return mapper.toDto(colorRepository.save(c));
		});
	}

	@Override
	@Transactional
	public Optional<ColorResponse> delete(Long id) {
		
		return colorRepository.findById(id).map(c -> {
			ColorResponse dto = mapper.toDto(c);
			colorRepository.delete(c);
			return dto;
		});
	}

}
