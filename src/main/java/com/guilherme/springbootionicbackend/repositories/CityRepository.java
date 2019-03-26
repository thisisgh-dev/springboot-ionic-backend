package com.guilherme.springbootionicbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guilherme.springbootionicbackend.domain.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

}
