package com.example.parkingapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.parkingapi.entity.ParkingLot;

public interface ParkingLotRepository extends JpaRepository<ParkingLot, Long> {

}
