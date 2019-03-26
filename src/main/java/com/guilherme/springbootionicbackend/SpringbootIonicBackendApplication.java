package com.guilherme.springbootionicbackend;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.guilherme.springbootionicbackend.domain.Category;
import com.guilherme.springbootionicbackend.domain.Product;
import com.guilherme.springbootionicbackend.repositories.CategoryRepository;
import com.guilherme.springbootionicbackend.repositories.ProductRepository;

@SpringBootApplication
public class SpringbootIonicBackendApplication implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootIonicBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Category cat1 = new Category(null, "Informatica");
		Category cat2 = new Category(null, "Escritorio");
		

		Product P1 = new Product(null, "Computador", 2000.00);
		Product P2 = new Product(null, "Impressora", 8000.00);
		Product P3 = new Product(null, "Mouse", 80.00);

		cat1.getProducts().addAll(Arrays.asList(P1, P2, P3));
		cat2.getProducts().addAll(Arrays.asList(P2));

		P1.getCategories().addAll(Arrays.asList(cat1));
		P2.getCategories().addAll(Arrays.asList(cat1, cat2));
		P3.getCategories().addAll(Arrays.asList(cat1));
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2));
		productRepository.saveAll(Arrays.asList(P1, P2, P3));
	}

}
