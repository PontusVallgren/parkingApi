package com.example.parkingapi.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.parkingapi.entity.Car;
import com.example.parkingapi.repository.CarRepository;

@RestController
@RequestMapping("/api")
public class CarController {

    CarRepository carRepo;

    public CarController(CarRepository carRepo) {
        this.carRepo = carRepo;
    }

    @GetMapping("/car")
    public List<Car> allCars() {
        return carRepo.findAll();
    }

    @GetMapping("/car/{id}")
    public Optional<Car> carById(@PathVariable Long id) {

        return carRepo.findById(id);
    }

    @PostMapping("/car")
    public ResponseEntity<Car> addPerson(@RequestBody Car car) {
        carRepo.save(car);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(car.getId())
                .toUri();

        return ResponseEntity.created(location).body(car);
    }

}
