package com.guilherme.springbootionicbackend.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.guilherme.springbootionicbackend.domain.Client;
import com.guilherme.springbootionicbackend.dto.ClientDTO;
import com.guilherme.springbootionicbackend.repositories.ClientRepository;
import com.guilherme.springbootionicbackend.services.exceptions.FieldMessage;

public class ClientUpdateValidator implements ConstraintValidator<ClientUpdate, ClientDTO> {
	
	@Autowired
	private HttpServletRequest request;
	
	
	@Autowired
	private ClientRepository repo;
	
	@Override
	public void initialize(ClientUpdate ann) {
	}

	@Override
	public boolean isValid(ClientDTO objDto, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String> ) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
		
		
		Client aux = repo.findByEmail(objDto.getEmail());
		if (aux != null && !aux.getId().equals(uriId)) {
			list.add(new FieldMessage("email", "This email already exists."));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}