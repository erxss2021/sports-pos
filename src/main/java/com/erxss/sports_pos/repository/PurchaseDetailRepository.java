package com.erxss.sports_pos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erxss.sports_pos.entity.PurchaseDetail;

public interface PurchaseDetailRepository extends JpaRepository<PurchaseDetail, Long>{
	List<PurchaseDetail> findByPurchaseId(Long purchaseId);

}
