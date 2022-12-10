package com.example.parkingapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.parkingapi.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
