package com.erxss.sports_pos.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erxss.sports_pos.dto.request.PurchaseDetailRequest;
import com.erxss.sports_pos.dto.response.PurchaseDetailResponse;
import com.erxss.sports_pos.entity.PurchaseDetail;
import com.erxss.sports_pos.mapper.PurchaseDetailMapper;
import com.erxss.sports_pos.repository.PurchaseDetailRepository;
import com.erxss.sports_pos.service.PurchaseDetailService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PurchaseDetailServiceImpl implements PurchaseDetailService {

	
	private final PurchaseDetailRepository detailRepository;
	private final PurchaseDetailMapper mapper;
	
	@Override
	@Transactional(readOnly = true)
	public List<PurchaseDetailResponse> findAll() {
		
		return detailRepository.findAll().stream().map(mapper::toDto).toList();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<PurchaseDetailResponse> findById(Long id) {
		
		return detailRepository.findById(id).map(mapper::toDto);
	}

	@Override
	@Transactional(readOnly = true)
	public List<PurchaseDetailResponse> findByPurhase(Long purchaseId) {
		
		return detailRepository.findByPurchaseId(purchaseId)
				.stream().map(mapper::toDto).toList();
	}

	@Override
	@Transactional
	public PurchaseDetailResponse create(PurchaseDetailRequest request) {
		PurchaseDetail detail = mapper.toEntity(request);
		
		return mapper.toDto(detailRepository.save(detail));
	}

	@Override
	@Transactional
	public Optional<PurchaseDetailResponse> delete(Long id) {
		
		return detailRepository.findById(id)
				.map(detail -> {
					detailRepository.delete(detail);
					return mapper.toDto(detail);
				});
	}

}
