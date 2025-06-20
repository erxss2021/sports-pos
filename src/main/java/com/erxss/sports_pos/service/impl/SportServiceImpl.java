package com.erxss.sports_pos.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erxss.sports_pos.dto.request.SportRequest;
import com.erxss.sports_pos.dto.response.SportResponse;
import com.erxss.sports_pos.mapper.SportMapper;
import com.erxss.sports_pos.repository.SportRepository;
import com.erxss.sports_pos.service.SportService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SportServiceImpl implements SportService {
	
	private final SportRepository sportRepository;
	private final SportMapper mapper;

	@Override
	@Transactional(readOnly = true)
	public List<SportResponse> findAll() {
		
		return sportRepository.findAll()
				.stream()
				.map(mapper::toDto)
				.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<SportResponse> findById(Long id) {
		
		return sportRepository.findById(id).map(mapper::toDto);
	}

	@Override
	@Transactional
	public SportResponse create(SportRequest request) {
		if(sportRepository.existsByNameIgnoreCase(request.getName())) {
			throw new IllegalArgumentException("Sport already exists");
		}
		return mapper.toDto(sportRepository.save(mapper.toEntity(request)));
	}

	@Override
	@Transactional
	public Optional<SportResponse> update(Long id, SportRequest request) {
		
		return sportRepository.findById(id).map(s -> {
			s.setName(request.getName());
			s.setDescription(request.getDescription());
			return mapper.toDto(sportRepository.save(s));
		});
	}

	@Override
	public Optional<SportResponse> delete(Long id) {
		
		return sportRepository.findById(id).map(s -> {
			SportResponse dto = mapper.toDto(s);
			sportRepository.delete(s);
			return dto;
		});
	}

}
