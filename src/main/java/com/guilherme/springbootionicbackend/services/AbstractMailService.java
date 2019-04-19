package com.guilherme.springbootionicbackend.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.guilherme.springbootionicbackend.domain.PurchaseOrder;

public abstract class AbstractMailService implements EmailService {

	@Value("${default.sender}")
	private String sender;

	@Override
	public void sendOrderConfirmationEmail(PurchaseOrder obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(PurchaseOrder obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getClient().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Pedido confirmado! Código: " + obj.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());
		return sm;
	}
}
