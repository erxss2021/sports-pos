package com.erxss.sports_pos.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erxss.sports_pos.dto.request.SupplierRequest;
import com.erxss.sports_pos.dto.response.SupplierResponse;
import com.erxss.sports_pos.mapper.SupplierMapper;
import com.erxss.sports_pos.repository.SupplierRepository;
import com.erxss.sports_pos.service.SupplierService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {
	
	private final SupplierRepository supplierRepository;
	private final SupplierMapper mapper;

	@Override
	@Transactional(readOnly = true)
	public List<SupplierResponse> findAll() {
		
		return supplierRepository.findAll()
				.stream()
				.map(mapper::toDto)
				.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<SupplierResponse> findById(Long id) {
		
		return supplierRepository.findById(id).map(mapper::toDto);
	}

	@Override
	@Transactional(readOnly = true)
	public List<SupplierResponse> searchName(String name) {
		
		return supplierRepository.findAll()
				.stream()
				.filter(s -> s.getName().toLowerCase().contains(name.toLowerCase()))
				.map(mapper::toDto)
				.collect(Collectors.toList());
	}

	@Override
	@Transactional
	public SupplierResponse create(SupplierRequest request) {
		if(supplierRepository.existsByEmail(request.getEmail()))
			throw new IllegalArgumentException("Supplier with this email already exists");
		return mapper.toDto(supplierRepository.save(mapper.toEntity(request)));
	}

	@Override
	@Transactional
	public Optional<SupplierResponse> update(Long id, SupplierRequest request) {
		
		return supplierRepository.findById(id).map(s -> {
			s.setName(request.getName());
			s.setEmail(request.getEmail());
			s.setPhone(request.getPhone());
			s.setAddress(request.getAddress());
			s.setTaxId(request.getTaxId());
			return mapper.toDto(supplierRepository.save(s));
		});
	}

	@Override
	@Transactional
	public Optional<SupplierResponse> delete(Long id) {
		
		return supplierRepository.findById(id).map(s -> {
			SupplierResponse dto = mapper.toDto(s);
			supplierRepository.delete(s);
			return dto;
		});
	}

}
