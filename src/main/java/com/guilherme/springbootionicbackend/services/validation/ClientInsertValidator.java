package com.guilherme.springbootionicbackend.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.guilherme.springbootionicbackend.domain.enums.ClientType;
import com.guilherme.springbootionicbackend.dto.ClientNewDTO;
import com.guilherme.springbootionicbackend.services.exceptions.FieldMessage;
import com.guilherme.springbootionicbackend.services.validation.utils.BR;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientNewDTO> {
	@Override
	public void initialize(ClientInsert ann) {
	}

	@Override
	public boolean isValid(ClientNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		
		
		
// inclua os testes aqui, inserindo erros na lista
		
		if(objDto.getType().equals(ClientType.NATURALPERSON.getCod()) && !BR.isValidCPF(objDto.getCpfOrCnpj())) {
			list.add(new FieldMessage("cpfOrCnpj", "Invalid CPF"));
		}
		
		if(objDto.getType().equals(ClientType.JURIDICALPERSON.getCod()) && !BR.isValidCNPJ(objDto.getCpfOrCnpj())) {
			list.add(new FieldMessage("cpfOrCnpj", "Invalid CNPJ"));
		}
		
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}