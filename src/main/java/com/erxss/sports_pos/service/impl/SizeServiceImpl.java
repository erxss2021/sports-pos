package com.erxss.sports_pos.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erxss.sports_pos.dto.request.SizeRequest;
import com.erxss.sports_pos.dto.response.SizeResponse;
import com.erxss.sports_pos.mapper.SizeMapper;
import com.erxss.sports_pos.repository.SizeRepository;
import com.erxss.sports_pos.service.SizeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SizeServiceImpl implements SizeService {
	
	private final SizeRepository sizeRepository;
	private final SizeMapper mapper;

	@Override
	@Transactional(readOnly = true)
	public List<SizeResponse> findAll() {
		
		return sizeRepository.findAll()
				.stream()
				.map(mapper::toDto)
				.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<SizeResponse> findById(Long id) {
		
		return sizeRepository.findById(id).map(mapper::toDto);
	}

	@Override
	@Transactional
	public SizeResponse create(SizeRequest request) {
		if(sizeRepository.existsByLabelIgnoreCase(request.getLabel())) {
			throw new IllegalArgumentException("Size already exists");
		}
		return mapper.toDto(sizeRepository.save(mapper.toEntity(request)));
	}

	@Override
	@Transactional
	public Optional<SizeResponse> update(Long id, SizeRequest request) {
		
		return sizeRepository.findById(id).map(s ->{
			s.setLabel(request.getLabel());
			s.setType(request.getType());
			return mapper.toDto(sizeRepository.save(s));
		});
	}

	@Override
	@Transactional
	public Optional<SizeResponse> delete(Long id) {
	
		return sizeRepository.findById(id).map(s -> {
			SizeResponse dto = mapper.toDto(s);
			sizeRepository.delete(s);
			return dto;
		});
	}

}
