package com.guilherme.springbootionicbackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.guilherme.springbootionicbackend.domain.Category;
import com.guilherme.springbootionicbackend.domain.Product;
import com.guilherme.springbootionicbackend.repositories.CategoryRepository;
import com.guilherme.springbootionicbackend.repositories.ProductRepository;
import com.guilherme.springbootionicbackend.services.exceptions.ObjectNotFountException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repo;
	
	@Autowired
	private CategoryRepository categoryRepository;

	public Product find(Integer id) {
		Optional<Product> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFountException(
				"Object not found! Id: " + id + ", Type: " + Product.class.getName()));
	}
	
	public Page<Product> search(String name, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Category> categories = categoryRepository.findAllById(ids);
		return repo.findDistinctByNameContainingAndCategoriesIn(name, categories, pageRequest);	
	
	}

}
