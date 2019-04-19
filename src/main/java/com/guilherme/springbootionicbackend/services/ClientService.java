package com.guilherme.springbootionicbackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guilherme.springbootionicbackend.domain.Address;
import com.guilherme.springbootionicbackend.domain.City;
import com.guilherme.springbootionicbackend.domain.Client;
import com.guilherme.springbootionicbackend.domain.enums.ClientType;
import com.guilherme.springbootionicbackend.dto.ClientDTO;
import com.guilherme.springbootionicbackend.dto.ClientNewDTO;
import com.guilherme.springbootionicbackend.repositories.AddressRepository;
import com.guilherme.springbootionicbackend.repositories.ClientRepository;
import com.guilherme.springbootionicbackend.services.exceptions.DataIntegrityException;
import com.guilherme.springbootionicbackend.services.exceptions.ObjectNotFountException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repo;

	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	public Client find(Integer id) {
		Optional<Client> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFountException("Object not found! Id: " + id + ", Type: " + Client.class.getName()));
	}
	
	@Transactional
	public Client insert(Client obj) {
		obj.setId(null);
		repo.save(obj);
		addressRepository.saveAll(obj.getAddresses());
		return obj;
	}
	
	public Client update(Client obj) {
		Client newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);

	}
	
	private void updateData(Client newObj, Client obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	
	}
	
	public void delete(Integer id) {
		find(id);
		try {
		repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("It is not possible to exclude because there are related order.");
			
		}
	}
	
	public List<Client> findAll(){
		return repo.findAll();
	}
	
	public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);		
	}
	
	public Client fromDto(ClientDTO objDto) {
		return new Client(objDto.getId(), objDto.getName(), objDto.getEmail(), null, null, null);
	}
	
	public Client fromDto(ClientNewDTO objDto) {
		Client client = new Client(null, objDto.getName(), objDto.getEmail(), objDto.getCpfOrCnpj(), ClientType.toEnum(objDto.getType()), pe.encode(objDto.getPassword()));
		City city = new City(objDto.getCityId(), null, null);
		Address address = new Address(null, objDto.getStreetAddress(), objDto.getNumber(), objDto.getComplement(), objDto.getNeighborhood(),objDto.getZipCode(), client, city);
		client.getAddresses().add(address);
		client.getPhoneNumber().add(objDto.getTelephone1());
		if (objDto.getTelephone2()!=null) {
			client.getPhoneNumber().add(objDto.getTelephone2());
		}
		return client;
	}
}