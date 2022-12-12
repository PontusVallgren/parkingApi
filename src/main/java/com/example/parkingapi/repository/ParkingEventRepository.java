package com.example.parkingapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.parkingapi.entity.ParkingEvent;

public interface ParkingEventRepository extends JpaRepository<ParkingEvent, Long> {

    List<ParkingEvent> findAllByIsActiveAndPersonId(Boolean active, Long personId);

    List<ParkingEvent> findAllByIsActiveAndCarId(Boolean active, Long carId);
}
