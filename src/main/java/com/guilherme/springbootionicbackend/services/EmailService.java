package com.guilherme.springbootionicbackend.services;

import org.springframework.mail.SimpleMailMessage;

import com.guilherme.springbootionicbackend.domain.PurchaseOrder;

public interface EmailService {
	
	void sendOrderConfirmationEmail(PurchaseOrder obj);

 	void sendEmail(SimpleMailMessage msg);

}
