package com.erxss.sports_pos.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erxss.sports_pos.dto.request.CustomerRequest;
import com.erxss.sports_pos.dto.response.CustomerResponse;
import com.erxss.sports_pos.mapper.CustomerMapper;
import com.erxss.sports_pos.repository.CustomerRepository;
import com.erxss.sports_pos.service.CustomerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository customerRepository;
	private final CustomerMapper mapper;
	
	@Override
	@Transactional(readOnly = true)
	public List<CustomerResponse> findAll() {
		
		return customerRepository.findAll().stream()
				.map(mapper::toDto)
				.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<CustomerResponse> findById(Long id) {
		
		return customerRepository.findById(id).map(mapper::toDto);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CustomerResponse> searchByname(String name) {
		
		return customerRepository.findAll().stream()
				.filter(c -> c.getFullName().toLowerCase().contains(name.toLowerCase()))
				.map(mapper::toDto)
				.collect(Collectors.toList());
	}

	@Override
	@Transactional
	public CustomerResponse create(CustomerRequest request) {
		if(customerRepository.existsByEmail(request.getEmail()))
			throw new IllegalArgumentException("Email already registered");
		if(customerRepository.existsByRfc(request.getRfc()))
			throw new IllegalArgumentException("Rfc Already registered");
		
		return mapper.toDto(customerRepository.save(mapper.toEntity(request)));
	}

	@Override
	@Transactional
	public Optional<CustomerResponse> update(Long id, CustomerRequest request) {
		
		return customerRepository.findById(id).map(c -> {
			c.setFullName(request.getFullName());
			c.setEmail(request.getEmail());
			c.setPhone(request.getPhone());
			c.setAddress(request.getAddress());
			c.setRfc(request.getRfc());
			return mapper.toDto(customerRepository.save(c));
		});
	}

	@Override
	@Transactional
	public Optional<CustomerResponse> delete(Long id) {
		
		return customerRepository.findById(id)
				.map(c -> {
					CustomerResponse dto = mapper.toDto(c);
					customerRepository.delete(c);
					return dto;
				});
	}

}
