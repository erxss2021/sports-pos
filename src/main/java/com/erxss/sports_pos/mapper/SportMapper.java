package com.erxss.sports_pos.mapper;

import org.springframework.stereotype.Component;

import com.erxss.sports_pos.dto.request.SportRequest;
import com.erxss.sports_pos.dto.response.SportResponse;
import com.erxss.sports_pos.entity.Sport;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SportMapper {

	public Sport toEntity(SportRequest dto) {
		Sport s = new Sport();
		s.setName(dto.getName());
		s.setDescription(dto.getDescription());
		return s;
	}
	
	public SportResponse toDto(Sport sport) {
		SportResponse dto = new SportResponse();
		dto.setId(sport.getId());
		dto.setName(sport.getName());
		dto.setDescription(sport.getDescription());
		return dto;
	}
}
