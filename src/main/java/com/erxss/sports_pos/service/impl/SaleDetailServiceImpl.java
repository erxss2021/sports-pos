package com.erxss.sports_pos.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erxss.sports_pos.dto.request.SaleDetailRequest;
import com.erxss.sports_pos.dto.response.SaleDetailResponse;
import com.erxss.sports_pos.entity.SaleDetail;
import com.erxss.sports_pos.mapper.SaleDetailMapper;
import com.erxss.sports_pos.repository.SaleDetailRepository;
import com.erxss.sports_pos.repository.SaleDetailService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaleDetailServiceImpl implements SaleDetailService {

	private final SaleDetailRepository detailRepository;
	private final SaleDetailMapper mapper;
	
	@Override
	@Transactional(readOnly = true)
	public List<SaleDetailResponse> findAll() {
		
		return detailRepository.findAll().stream().map(mapper::toDto).toList();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<SaleDetailResponse> findById(Long id) {
		
		return detailRepository.findById(id).map(mapper::toDto);
	}

	@Override
	@Transactional(readOnly = true)
	public List<SaleDetailResponse> findbySale(Long saleId) {
		
		return detailRepository.findBySaleId(saleId)
				.stream()
				.map(mapper::toDto)
				.toList();
	}

	@Override
	@Transactional
	public SaleDetailResponse create(SaleDetailRequest request) {
		SaleDetail detail = mapper.toEntity(request);
		return mapper.toDto(detailRepository.save(detail));
	}

	@Override
	@Transactional
	public Optional<SaleDetailResponse> delete(Long id) {
		
		return detailRepository.findById(id)
				.map(detail -> {
					detailRepository.delete(detail);
					return mapper.toDto(detail);
				});
	}

}
