package com.guilherme.springbootionicbackend.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilherme.springbootionicbackend.domain.Address;
import com.guilherme.springbootionicbackend.domain.Category;
import com.guilherme.springbootionicbackend.domain.City;
import com.guilherme.springbootionicbackend.domain.Client;
import com.guilherme.springbootionicbackend.domain.ItemOrder;
import com.guilherme.springbootionicbackend.domain.Payment;
import com.guilherme.springbootionicbackend.domain.PaymentWithCard;
import com.guilherme.springbootionicbackend.domain.PaymentWithTicket;
import com.guilherme.springbootionicbackend.domain.Product;
import com.guilherme.springbootionicbackend.domain.PurchaseOrder;
import com.guilherme.springbootionicbackend.domain.State;
import com.guilherme.springbootionicbackend.domain.enums.ClientType;
import com.guilherme.springbootionicbackend.domain.enums.StatusPayment;
import com.guilherme.springbootionicbackend.repositories.AddressRepository;
import com.guilherme.springbootionicbackend.repositories.CategoryRepository;
import com.guilherme.springbootionicbackend.repositories.CityRepository;
import com.guilherme.springbootionicbackend.repositories.ClientRepository;
import com.guilherme.springbootionicbackend.repositories.ItemOrderRepository;
import com.guilherme.springbootionicbackend.repositories.PaymentRepository;
import com.guilherme.springbootionicbackend.repositories.ProductRepository;
import com.guilherme.springbootionicbackend.repositories.PurchaseOrderRepository;
import com.guilherme.springbootionicbackend.repositories.StateRepository;


@Service
public class DBService {
	
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

	@Autowired
	private PurchaseOrderRepository pucheaseOrderRepository;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private ItemOrderRepository itemOrderRepository;
	
		
		public void instantiateTestDatabase() throws ParseException {
		
		Category cat1 = new Category(null, "Informatica");
		Category cat2 = new Category(null, "Escritorio");
		Category cat3 = new Category(null, "Cama Mesa e Banho");
		Category cat4 = new Category(null, "Eletronicos");
		Category cat5 = new Category(null, "Jardinagem");
		Category cat6 = new Category(null, "Decoraçao");
		Category cat7 = new Category(null, "Perfumaria");
		
		Product P1 = new Product(null, "Computador", 2000.00);
		Product P2 = new Product(null, "Impressora", 800.00);
		Product P3 = new Product(null, "Mouse", 80.00);
		Product P4 = new Product(null, "Mesa de escritório", 300.00);
		Product P5 = new Product(null, "Toalha", 50.00);
		Product P6 = new Product(null, "Colcha", 200.00);
		Product P7 = new Product(null, "TV true color", 1200.00);
		Product P8 = new Product(null, "Roçadeira", 800.00);
		Product P9 = new Product(null, "Abajour", 100.00);
		Product P10 = new Product(null, "Pendente", 180.00);
		Product P11 = new Product(null, "Shampoo", 90.00);

		cat1.getProducts().addAll(Arrays.asList(P1, P2, P3));
		cat2.getProducts().addAll(Arrays.asList(P2));
		cat2.getProducts().addAll(Arrays.asList(P2, P4));
		cat3.getProducts().addAll(Arrays.asList(P5, P6));
		cat4.getProducts().addAll(Arrays.asList(P1, P2, P3, P7));
		cat5.getProducts().addAll(Arrays.asList(P8));
		cat6.getProducts().addAll(Arrays.asList(P9, P10));
		cat7.getProducts().addAll(Arrays.asList(P11));
		
 		P1.getCategories().addAll(Arrays.asList(cat1, cat4));
		P2.getCategories().addAll(Arrays.asList(cat1, cat2, cat4));
		P3.getCategories().addAll(Arrays.asList(cat1, cat4));
		P4.getCategories().addAll(Arrays.asList(cat2));
		P5.getCategories().addAll(Arrays.asList(cat3));
		P6.getCategories().addAll(Arrays.asList(cat3));
		P7.getCategories().addAll(Arrays.asList(cat4));
		P8.getCategories().addAll(Arrays.asList(cat5));
		P9.getCategories().addAll(Arrays.asList(cat6));
		P10.getCategories().addAll(Arrays.asList(cat6));
		P11.getCategories().addAll(Arrays.asList(cat7));	

		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		productRepository.saveAll(Arrays.asList(P1, P2, P3,  P4, P5, P6, P7, P8, P9, P10, P11));

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

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		PurchaseOrder order1 = new PurchaseOrder(null, sdf.parse("30/09/2017 10:32"), cli1, ad1);
		PurchaseOrder order2 = new PurchaseOrder(null, sdf.parse("10/10/2017 19:35"), cli1, ad2);

		cli1.getOrders().addAll(Arrays.asList(order1, order2));

		Payment pay1 = new PaymentWithCard(null, StatusPayment.SETTLED, order1, 6);

		order1.setPayment(pay1);

		Payment pay2 = new PaymentWithTicket(null, StatusPayment.PENDING, order2, sdf.parse("20/10/2017 00:00"), null);

		order2.setPayment(pay2);

		pucheaseOrderRepository.saveAll(Arrays.asList(order1, order2));
		paymentRepository.saveAll(Arrays.asList(pay1, pay2));

		ItemOrder it1 = new ItemOrder(order1, P1, 0.00, 1, 2000.00);
		ItemOrder it2 = new ItemOrder(order1, P3, 0.00, 2, 80.00);
		ItemOrder it3 = new ItemOrder(order2, P2, 100.00, 1, 800.00);

		order1.getItens().addAll(Arrays.asList(it1, it2));
		order2.getItens().addAll(Arrays.asList(it3));

		P1.getItens().addAll(Arrays.asList(it1));
		P2.getItens().addAll(Arrays.asList(it3));
		P3.getItens().addAll(Arrays.asList(it2));

		itemOrderRepository.saveAll(Arrays.asList(it1, it2, it3));
		}
	}

