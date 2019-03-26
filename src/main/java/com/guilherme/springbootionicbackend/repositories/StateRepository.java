package com.guilherme.springbootionicbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guilherme.springbootionicbackend.domain.State;

@Repository
public interface StateRepository extends JpaRepository<State, Integer> {

}
