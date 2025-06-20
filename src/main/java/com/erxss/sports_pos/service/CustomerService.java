package com.erxss.sports_pos.service;

import java.util.List;
import java.util.Optional;

import com.erxss.sports_pos.dto.request.CustomerRequest;
import com.erxss.sports_pos.dto.response.CustomerResponse;

public interface CustomerService {
	List<CustomerResponse> findAll();
	Optional<CustomerResponse> findById(Long id);
	List<CustomerResponse> searchByname(String name);
	CustomerResponse create(CustomerRequest request);
	Optional<CustomerResponse> update(Long id, CustomerRequest request);
	Optional<CustomerResponse> delete(Long id);

}
