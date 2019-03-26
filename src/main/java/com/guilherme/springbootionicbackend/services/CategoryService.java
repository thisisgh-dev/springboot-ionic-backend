package com.guilherme.springbootionicbackend.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilherme.springbootionicbackend.domain.Category;
import com.guilherme.springbootionicbackend.repositories.CategoryRepository;
import com.guilherme.springbootionicbackend.services.exceptions.ObjectNotFountException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repo;

	public Category find(Integer id) {
		Optional<Category> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFountException("Object not found! Id: " + id + ", Type: " + Category.class.getName()));
	}

}
