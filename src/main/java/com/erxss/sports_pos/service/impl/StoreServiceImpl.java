package com.erxss.sports_pos.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erxss.sports_pos.dto.request.StoreRequest;
import com.erxss.sports_pos.dto.response.StoreResponse;
import com.erxss.sports_pos.entity.Store;
import com.erxss.sports_pos.mapper.StoreMapper;
import com.erxss.sports_pos.repository.StoreRepository;
import com.erxss.sports_pos.service.StoreService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

	private final StoreRepository storeRepository;
	private final StoreMapper mapper;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<StoreResponse> findAll() {
		
		return storeRepository.findAll().stream().map(mapper::toDto).toList();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<StoreResponse> findById(Long id) {
		
		return storeRepository.findById(id).map(mapper::toDto);
	}

	@Override
	@Transactional
	public StoreResponse create(StoreRequest request) {
		if(storeRepository.existsByName(request.getName()))
			throw new IllegalArgumentException("Store with that name already exists");
		Store store = mapper.toEntity(request);
		
		return mapper.toDto(storeRepository.save(store));
	}

	@Override
	@Transactional
	public Optional<StoreResponse> update(Long id, StoreRequest request) {
		
		return storeRepository.findById(id).map(store -> {
			mapper.update(store, request);
			return mapper.toDto(storeRepository.save(store));
		});
	}

	@Override
	@Transactional
	public Optional<StoreResponse> delete(Long id) {
		
		return storeRepository.findById(id).map(store -> {
			storeRepository.delete(store);
			return mapper.toDto(store);
		});
	}

}
