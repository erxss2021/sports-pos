package com.erxss.sports_pos.service;

import java.util.List;
import java.util.Optional;

import com.erxss.sports_pos.dto.request.SupplierRequest;
import com.erxss.sports_pos.dto.response.SupplierResponse;

public interface SupplierService {
	
	List<SupplierResponse> findAll();
	Optional<SupplierResponse> findById(Long id);
	List<SupplierResponse> searchName(String name);
	SupplierResponse create(SupplierRequest request);
	Optional<SupplierResponse> update(Long id, SupplierRequest request);
	Optional<SupplierResponse> delete(Long id);
	

}
