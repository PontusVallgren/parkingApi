package com.example.parkingapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.parkingapi.entity.Car;

public interface CarRepository extends JpaRepository<Car, Long> {

}
