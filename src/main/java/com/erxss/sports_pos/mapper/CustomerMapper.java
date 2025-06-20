package com.erxss.sports_pos.mapper;

import org.springframework.stereotype.Component;

import com.erxss.sports_pos.dto.request.CustomerRequest;
import com.erxss.sports_pos.dto.response.CustomerResponse;
import com.erxss.sports_pos.entity.Customer;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomerMapper {

	public Customer toEntity(CustomerRequest dto) {
		Customer c = new Customer();
		c.setFullName(dto.getFullName());
		c.setEmail(dto.getEmail());
		c.setPhone(dto.getPhone());
		c.setAddress(dto.getAddress());
		c.setRfc(dto.getRfc());
		return c;
	}
	
	public CustomerResponse toDto(Customer customer) {
		CustomerResponse dto = new CustomerResponse();
		dto.setId(customer.getId());
		dto.setFullName(customer.getFullName());
		dto.setEmail(customer.getEmail());
		dto.setPhone(customer.getPhone());
		dto.setAddress(customer.getAddress());
		dto.setRfc(customer.getRfc());
		return dto;
	}
}
