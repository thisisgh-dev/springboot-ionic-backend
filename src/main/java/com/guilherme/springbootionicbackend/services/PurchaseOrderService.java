package com.guilherme.springbootionicbackend.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilherme.springbootionicbackend.domain.PurchaseOrder;
import com.guilherme.springbootionicbackend.repositories.PurchaseOrderRepository;
import com.guilherme.springbootionicbackend.services.exceptions.ObjectNotFountException;

@Service
public class PurchaseOrderService {

	@Autowired
	private PurchaseOrderRepository repo;

	public PurchaseOrder find(Integer id) {
		Optional<PurchaseOrder> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFountException(
				"Object not found! Id: " + id + ", Type: " + PurchaseOrder.class.getName()));
	}

}
