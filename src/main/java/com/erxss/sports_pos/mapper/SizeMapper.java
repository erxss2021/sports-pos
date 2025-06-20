package com.erxss.sports_pos.mapper;

import org.springframework.stereotype.Component;

import com.erxss.sports_pos.dto.request.SizeRequest;
import com.erxss.sports_pos.dto.response.SizeResponse;
import com.erxss.sports_pos.entity.Size;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SizeMapper {

	public Size toEntity(SizeRequest dto) {
		Size s = new Size();
		s.setLabel(dto.getLabel());
		s.setType(dto.getType());
		return s;
	}
	
	public SizeResponse toDto(Size size) {
		SizeResponse dto = new SizeResponse();
		dto.setId(size.getId());
		dto.setLabel(size.getLabel());
		dto.setType(size.getType());
		return dto;
	}
}
