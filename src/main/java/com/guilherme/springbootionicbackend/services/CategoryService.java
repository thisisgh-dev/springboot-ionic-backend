package com.guilherme.springbootionicbackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.guilherme.springbootionicbackend.domain.Category;
import com.guilherme.springbootionicbackend.repositories.CategoryRepository;
import com.guilherme.springbootionicbackend.services.exceptions.DataIntegrityException;
import com.guilherme.springbootionicbackend.services.exceptions.ObjectNotFountException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repo;

	public Category find(Integer id) {
		Optional<Category> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFountException("Object not found! Id: " + id + ", Type: " + Category.class.getName()));
	}

	public Category insert(Category obj) {
		obj.setId(null);
		return repo.save(obj);
		
	}
	
	public Category update(Category obj) {
		find(obj.getId());
		return repo.save(obj);

	}
	
	public void delete(Integer id) {
		find(id);
		try {
		repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("It is not possible to exclude a category containing products.");
			
		}
	}
	
	public List<Category> findAll(){
		return repo.findAll();
	}
	
}
	