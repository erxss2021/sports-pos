package com.erxss.sports_pos.mapper;

import org.springframework.stereotype.Component;


import com.erxss.sports_pos.dto.request.StoreRequest;
import com.erxss.sports_pos.dto.response.StoreResponse;
import com.erxss.sports_pos.entity.Store;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class StoreMapper {

	
	public Store toEntity(StoreRequest dto) {
		Store store = new Store();
		store.setName(dto.getName());
		store.setAddress(dto.getAddress());
		store.setEmail(dto.getEmail());
		store.setPhone(dto.getPhone());
		return store;
	}
	
	public StoreResponse toDto(Store store) {
		StoreResponse dto = new StoreResponse();
		
		dto.setId(store.getId());
		dto.setName(store.getName());
		dto.setAddress(store.getAddress());
		dto.setEmail(store.getEmail());
		dto.setPhone(store.getPhone());
		
		return dto;
	}
	
	public void update(Store store, StoreRequest dto) {
		store.setName(dto.getName());
		store.setAddress(dto.getAddress());
		store.setEmail(dto.getEmail());
		store.setPhone(dto.getPhone());
	}
}
