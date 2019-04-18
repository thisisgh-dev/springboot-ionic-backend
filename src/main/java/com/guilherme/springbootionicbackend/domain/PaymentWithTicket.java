package com.guilherme.springbootionicbackend.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.guilherme.springbootionicbackend.domain.enums.StatusPayment;

@Entity
@JsonTypeName("PaymentWithTicket")
public class PaymentWithTicket extends Payment {
	private static final long serialVersionUID = 1L;

	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dueDate;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date payDate;

	public PaymentWithTicket() {
	}

	public PaymentWithTicket(Integer id, StatusPayment status, PurchaseOrder order, Date dueDate, Date payDate) {
		super(id, status, order);
		this.dueDate = dueDate;
		this.payDate = payDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
}
