package com.example.parkingapi.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.parkingapi.entity.Car;
import com.example.parkingapi.entity.ParkingEvent;
import com.example.parkingapi.entity.ParkingLot;
import com.example.parkingapi.entity.Person;
import com.example.parkingapi.repository.CarRepository;
import com.example.parkingapi.repository.ParkingEventRepository;
import com.example.parkingapi.repository.ParkingLotRepository;
import com.example.parkingapi.repository.PersonRepository;

@RestController
@RequestMapping("/api")
public class ParkingEventController {

    ParkingEventRepository parkingEventRepo;
    CarRepository carRepo;
    ParkingLotRepository parkRepo;
    PersonRepository personRepo;

    public ParkingEventController(ParkingEventRepository parkingEventRepo, CarRepository carRepo,
            ParkingLotRepository parkRepo, PersonRepository personRepo) {
        this.parkingEventRepo = parkingEventRepo;
        this.carRepo = carRepo;
        this.parkRepo = parkRepo;
        this.personRepo = personRepo;
    }

    @GetMapping("/parkingEvent")
    public List<ParkingEvent> getAllParkingEvents(@RequestParam(required = false) Boolean active) {
        if (active == null) {
            return parkingEventRepo.findAll();
        }

        List<ParkingEvent> filteredEvents = parkingEventRepo.findAll().stream()
                .filter(events -> events.getIsActive() == active).toList();

        return filteredEvents;
    }

    @GetMapping("/parkingEvent/{id}")
    public Optional<ParkingEvent> getParkingEventsById(@PathVariable Long id) {
        return parkingEventRepo.findById(id);
    }

    @GetMapping("/parkingEvent/person/{personId}")
    public List<ParkingEvent> getParkingEventByPersonId(@RequestParam Boolean active,
            @PathVariable Long personId) {
        return parkingEventRepo.findAllByIsActiveAndPersonId(active, personId);
    }

    @GetMapping("/parkingEvent/car/{carId}")
    public List<ParkingEvent> getParkingeventByCarId(@RequestParam Boolean active, @PathVariable Long carId) {
        return parkingEventRepo.findAllByIsActiveAndCarId(active, carId);
    }

    @PostMapping("parkingEvent")
    public ParkingEvent createParkingEvent(@RequestBody ParkingEvent parkingEvent) {

        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime stopTime = parkingEvent.getStopTime();

        if (stopTime.isBefore(startTime)) {
            throw new RuntimeErrorException(null, "Stop time is before start time");
        }

        Long personId = parkingEvent.getPerson().getId();
        Person person = personRepo.findById(personId).orElseThrow();
        parkingEvent.setPerson(person);

        Long parkingLotId = parkingEvent.getParkingLot().getId();
        ParkingLot parkingLot = parkRepo.findById(parkingLotId).orElseThrow();
        parkingEvent.setParkingLot(parkingLot);

        Long carId = parkingEvent.getCar().getId();
        Car car = carRepo.findById(carId).orElseThrow();
        parkingEvent.setCar(car);

        parkingEvent.setStartTime(startTime);
        parkingEvent.setStopTime(stopTime);
        parkingEvent.setIsActive(true);

        return parkingEventRepo.save(parkingEvent);

    }

    @PutMapping("/parkingEvent/{id}")
    public ParkingEvent updateParkingEvent(@PathVariable Long id, @RequestBody ParkingEvent parkingEvent) {
        LocalDateTime newStopTime = parkingEvent.getStopTime();

        ParkingEvent updatedParkingEvent = parkingEventRepo.findById(id).orElseThrow();
        updatedParkingEvent.setStopTime(newStopTime);

        return parkingEventRepo.save(updatedParkingEvent);
    }

}
