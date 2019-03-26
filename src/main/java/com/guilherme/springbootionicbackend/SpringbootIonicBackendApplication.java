package com.guilherme.springbootionicbackend;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.guilherme.springbootionicbackend.domain.Category;
import com.guilherme.springbootionicbackend.repositories.CategoryRepository;

@SpringBootApplication
public class SpringbootIonicBackendApplication implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootIonicBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Category cat1 = new Category(null, "Informatica");
		Category cat2 = new Category(null, "Escritorio");
		categoryRepository.saveAll(Arrays.asList(cat1, cat2));
	}

}
