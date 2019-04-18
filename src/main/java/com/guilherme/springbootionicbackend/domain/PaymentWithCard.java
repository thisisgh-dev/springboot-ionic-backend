package com.guilherme.springbootionicbackend.domain;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.guilherme.springbootionicbackend.domain.enums.StatusPayment;

@Entity
@JsonTypeName("PaymentWithCard")
public class PaymentWithCard extends Payment {
	private static final long serialVersionUID = 1L;

	private Integer numberOfInstallments;

	public PaymentWithCard() {
	}

	public PaymentWithCard(Integer id, StatusPayment status, PurchaseOrder order, Integer numberOfInstallments) {
		super(id, status, order);
		this.numberOfInstallments = numberOfInstallments;
	}

	public Integer getNumberOfInstallments() {
		return numberOfInstallments;
	}

	public void setNumberOfInstallments(Integer numberOfInstallments) {
		this.numberOfInstallments = numberOfInstallments;
	}
}
