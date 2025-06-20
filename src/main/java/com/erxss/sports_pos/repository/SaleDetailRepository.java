package com.erxss.sports_pos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erxss.sports_pos.entity.SaleDetail;

public interface SaleDetailRepository extends JpaRepository<SaleDetail, Long> {
	List<SaleDetail> findBySaleId(Long saleId);

}
