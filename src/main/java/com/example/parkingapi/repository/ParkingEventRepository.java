package com.example.parkingapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.parkingapi.entity.ParkingEvent;

public interface ParkingEventRepository extends JpaRepository<ParkingEvent, Long> {

}
