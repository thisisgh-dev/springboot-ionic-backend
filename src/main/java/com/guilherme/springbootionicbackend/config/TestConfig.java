package com.guilherme.springbootionicbackend.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.guilherme.springbootionicbackend.services.DBService;
import com.guilherme.springbootionicbackend.services.EmailService;
import com.guilherme.springbootionicbackend.services.MockEmailService;

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private DBService dbService;

	@Bean
	public boolean instatiateDatabase() throws ParseException {
		dbService.instantiateTestDatabase();

		return true;
	}

	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}

}
