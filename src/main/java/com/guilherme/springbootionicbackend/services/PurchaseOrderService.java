package com.guilherme.springbootionicbackend.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guilherme.springbootionicbackend.domain.Client;
import com.guilherme.springbootionicbackend.domain.ItemOrder;
import com.guilherme.springbootionicbackend.domain.PaymentWithTicket;
import com.guilherme.springbootionicbackend.domain.PurchaseOrder;
import com.guilherme.springbootionicbackend.domain.enums.StatusPayment;
import com.guilherme.springbootionicbackend.repositories.ItemOrderRepository;
import com.guilherme.springbootionicbackend.repositories.PaymentRepository;
import com.guilherme.springbootionicbackend.repositories.PurchaseOrderRepository;
import com.guilherme.springbootionicbackend.security.UserSS;
import com.guilherme.springbootionicbackend.services.exceptions.AuthorizationException;
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
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private EmailService emailService;

	public PurchaseOrder find(Integer id) {
		Optional<PurchaseOrder> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFountException(
				"Object not found! Id: " + id + ", Type: " + PurchaseOrder.class.getName()));
	}
	
	@Transactional
	public PurchaseOrder insert(PurchaseOrder obj) {
		obj.setId(null);
		obj.setInstant(new Date());
		obj.setClient(clientService.find(obj.getClient().getId()));
		obj.getPayment().setStatus(StatusPayment.PENDING);
		obj.getPayment().setOrder(obj);
		if (obj.getPayment() instanceof PaymentWithTicket) {
			PaymentWithTicket pay = (PaymentWithTicket) obj.getPayment();
			ticketService.FillPaymentWithTicket(pay, obj.getInstant());
		}
		
		obj = repo.save(obj);
		paymentRepository.save(obj.getPayment());
		for (ItemOrder io : obj.getItens()) {
			io.setDiscount(0.0);
			io.setProduct(productService.find(io.getProduct().getId()));
			io.setPrice(io.getProduct().getPrice());
			io.setPrice(productService.find(io.getProduct().getId()).getPrice());
			io.setPurchaseOrder(obj);
		}
		itemOrderRepository.saveAll(obj.getItens());
		emailService.sendOrderConfirmationHtmlEmail(obj);
		return obj;		
	}
	
	public Page<PurchaseOrder> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Access denied");
		}
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Client client =  clientService.find(user.getId());
		return repo.findByClient(client, pageRequest);
	}
}
