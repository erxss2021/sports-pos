package com.erxss.sports_pos.mapper;

import org.springframework.stereotype.Component;

import com.erxss.sports_pos.dto.request.ColorRequest;
import com.erxss.sports_pos.dto.response.ColorResponse;
import com.erxss.sports_pos.entity.Color;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ColorMapper {
	
	public Color toEntity(ColorRequest dto) {
		Color c = new Color();
		c.setName(dto.getName());
		c.setHexCode(dto.getHexCode());
		return c;
	}
	
	public ColorResponse toDto(Color color) {
		ColorResponse dto = new ColorResponse();
		dto.setId(color.getId());
		dto.setName(color.getName());
		dto.setHexCode(color.getHexCode());
		return dto;
	}
}
