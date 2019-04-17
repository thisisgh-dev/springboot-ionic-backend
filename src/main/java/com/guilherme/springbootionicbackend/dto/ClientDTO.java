package com.guilherme.springbootionicbackend.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.guilherme.springbootionicbackend.domain.Client;

public class ClientDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty (message = "This field can not be empty")
	@Length (min = 5, max = 120, message = "The size must be between 5 and 120 characters")
	private String name;
	
	@NotEmpty (message = "This field can not be empty")
	@Email (message = "Invalid Email")
	private String email;
	
	public ClientDTO() {
		
	}

	public ClientDTO(Client obj) {
		id = obj.getId();
		name = obj.getName();
		email = obj.getEmail();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
