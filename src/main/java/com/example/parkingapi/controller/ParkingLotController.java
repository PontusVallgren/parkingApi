package com.example.parkingapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.parkingapi.entity.ParkingLot;
import com.example.parkingapi.repository.ParkingLotRepository;

@RestController
@RequestMapping("/api")
public class ParkingLotController {

    ParkingLotRepository parkingLotRepo;

    public ParkingLotController(ParkingLotRepository parkingLotRepo) {
        this.parkingLotRepo = parkingLotRepo;
    }

    @GetMapping("/parkingLot")
    public List<ParkingLot> allParkingLots() {
        return parkingLotRepo.findAll();
    }

    @GetMapping("/parkingLot/{id}")
    public Optional<ParkingLot> findParkingLotById(@PathVariable Long id) {
        return parkingLotRepo.findById(id);
    }

    @PostMapping("/parkingLot")
    public ParkingLot createNewParkingLot(@RequestBody ParkingLot parkingLot) {
        return parkingLotRepo.save(parkingLot);
    }
}
