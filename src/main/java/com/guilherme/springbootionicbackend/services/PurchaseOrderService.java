package com.guilherme.springbootionicbackend.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guilherme.springbootionicbackend.domain.ItemOrder;
import com.guilherme.springbootionicbackend.domain.PaymentWithTicket;
import com.guilherme.springbootionicbackend.domain.PurchaseOrder;
import com.guilherme.springbootionicbackend.domain.enums.StatusPayment;
import com.guilherme.springbootionicbackend.repositories.ItemOrderRepository;
import com.guilherme.springbootionicbackend.repositories.PaymentRepository;
import com.guilherme.springbootionicbackend.repositories.PurchaseOrderRepository;
import com.guilherme.springbootionicbackend.services.exceptions.ObjectNotFountException;

@Service
public class PurchaseOrderService {

	@Autowired
	private PurchaseOrderRepository repo;
	
	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private ItemOrderRepository itemOrderRepository;
	
	@Autowired
	private ProductService productService;

	public PurchaseOrder find(Integer id) {
		Optional<PurchaseOrder> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFountException(
				"Object not found! Id: " + id + ", Type: " + PurchaseOrder.class.getName()));
	}
	
	@Transactional
	public PurchaseOrder insert(PurchaseOrder obj) {
		obj.setId(null);
		obj.setInstant(new Date());
		obj.getPayment().setStatus(StatusPayment.PENDING);
		obj.getPayment().setOrder(obj);
		if (obj.getPayment() instanceof PaymentWithTicket) {
			PaymentWithTicket pay = (PaymentWithTicket) obj.getPayment();
			ticketService.FillPaymentWithTicket(pay, obj.getInstant());
		}
		
		obj = repo.save(obj);
		paymentRepository.save(obj.getPayment());
		for (ItemOrder ip : obj.getItens()) {
			ip.setDiscount(0.0);
			ip.setPrice(productService.find(ip.getProduct().getId()).getPrice());
			ip.setPurchaseOrder(obj);
		}
		itemOrderRepository.saveAll(obj.getItens());
		return obj;
		
		
	}
	
}
