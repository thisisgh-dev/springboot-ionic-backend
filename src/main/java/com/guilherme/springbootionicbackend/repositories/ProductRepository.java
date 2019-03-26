package com.guilherme.springbootionicbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guilherme.springbootionicbackend.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
