package com.guilherme.springbootionicbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guilherme.springbootionicbackend.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
