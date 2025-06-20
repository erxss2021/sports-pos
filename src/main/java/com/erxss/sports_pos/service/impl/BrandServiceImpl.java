package com.erxss.sports_pos.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erxss.sports_pos.dto.request.BrandRequest;
import com.erxss.sports_pos.dto.response.BrandResponse;
import com.erxss.sports_pos.mapper.BrandMapper;
import com.erxss.sports_pos.repository.BrandRepository;
import com.erxss.sports_pos.service.BrandService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {
	
	private final BrandRepository brandRepository;
	private final BrandMapper mapper;

	@Override
	@Transactional(readOnly = true)
	public List<BrandResponse> findAll() {
		
		return brandRepository.findAll()
				.stream()
				.map(mapper::toDto)
				.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<BrandResponse> findById(Long id) {

		return brandRepository.findById(id)
				.map(mapper::toDto);
	}

	@Override
	@Transactional
	public BrandResponse create(BrandRequest request) {
		if(brandRepository.existsByNameIgnoreCase(request.getName())) {
			throw new IllegalArgumentException("Brand already exists");
		}
		return mapper.toDto(brandRepository.save(mapper.toEntity(request)));
	}

	@Override
	@Transactional
	public Optional<BrandResponse> update(Long id, BrandRequest request) {
		
		return brandRepository.findById(id).map(b -> {
			b.setName(request.getName());
			b.setDescription(request.getDescription());
			return mapper.toDto(brandRepository.save(b));
		});
	}

	@Override
	@Transactional
	public Optional<BrandResponse> delete(Long id) {
		
		return brandRepository.findById(id).map(b -> {
			BrandResponse dto = mapper.toDto(b);
			brandRepository.delete(b);
			return dto;
		});
	}

}
