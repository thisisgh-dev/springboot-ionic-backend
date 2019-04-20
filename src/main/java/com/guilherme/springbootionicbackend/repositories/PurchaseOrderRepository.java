package com.guilherme.springbootionicbackend.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.guilherme.springbootionicbackend.domain.Client;
import com.guilherme.springbootionicbackend.domain.PurchaseOrder;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Integer> {

	@Transactional(readOnly = true)
	Page<PurchaseOrder> findByClient(Client client, Pageable pageRequest);

}
