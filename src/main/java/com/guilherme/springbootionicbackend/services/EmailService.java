package com.guilherme.springbootionicbackend.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.guilherme.springbootionicbackend.domain.Client;
import com.guilherme.springbootionicbackend.domain.PurchaseOrder;

public interface EmailService {
	
	void sendOrderConfirmationEmail(PurchaseOrder obj);

 	void sendEmail(SimpleMailMessage msg);
 	
 	void sendOrderConfirmationHtmlEmail(PurchaseOrder obj);

 	void sendHtmlEmail(MimeMessage msg);
 	
 	void sendNewPasswordEmail(Client client, String newPass);

}
