package com.guilherme.springbootionicbackend;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.guilherme.springbootionicbackend.domain.Address;
import com.guilherme.springbootionicbackend.domain.Category;
import com.guilherme.springbootionicbackend.domain.City;
import com.guilherme.springbootionicbackend.domain.Client;
import com.guilherme.springbootionicbackend.domain.Product;
import com.guilherme.springbootionicbackend.domain.State;
import com.guilherme.springbootionicbackend.domain.enums.ClientType;
import com.guilherme.springbootionicbackend.repositories.AddressRepository;
import com.guilherme.springbootionicbackend.repositories.CategoryRepository;
import com.guilherme.springbootionicbackend.repositories.CityRepository;
import com.guilherme.springbootionicbackend.repositories.ClientRepository;
import com.guilherme.springbootionicbackend.repositories.ProductRepository;
import com.guilherme.springbootionicbackend.repositories.StateRepository;

@SpringBootApplication
public class SpringbootIonicBackendApplication implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private AddressRepository addressRepository;

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

		State stt1 = new State(null, "Minas Gerais");
		State stt2 = new State(null, "Sao Paulo");

		City cty1 = new City(null, "Uberlandia", stt1);
		City cty2 = new City(null, "Sao Paulo", stt2);
		City cty3 = new City(null, "Campinas", stt2);

		stt1.getCities().addAll(Arrays.asList(cty1));
		stt2.getCities().addAll(Arrays.asList(cty2, cty3));

		stateRepository.saveAll(Arrays.asList(stt1, stt2));
		cityRepository.saveAll(Arrays.asList(cty1, cty2, cty3));

		Client cli1 = new Client(null, "Maria Silva", "maria@gmail.com", "02316328891", ClientType.NATURALPERSON);
		cli1.getPhoneNumber().addAll(Arrays.asList("9886644", "9786644"));

		Address ad1 = new Address(null, "Rua Flores", "300", "Apto 303", "Jardim", "40478349", cli1, cty1);
		Address ad2 = new Address(null, "Avenida Matos", "105", "Sala 800", "Centro", "43028336", cli1, cty2);

		cli1.getAddresses().addAll(Arrays.asList(ad1, ad2));

		clientRepository.saveAll(Arrays.asList(cli1));
		addressRepository.saveAll(Arrays.asList(ad1, ad2));
	}
}
