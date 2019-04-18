package com.guilherme.springbootionicbackend.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.guilherme.springbootionicbackend.domain.PaymentWithTicket;

@Service
public class TicketService {
	
	public void FillPaymentWithTicket(PaymentWithTicket pay, Date instantOfOrder) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instantOfOrder);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pay.setDueDate(cal.getTime());
				
	}

}
