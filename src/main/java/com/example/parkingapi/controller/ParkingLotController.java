package com.example.parkingapi.controller;

import java.util.List;
import java.util.Optional;

import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.parkingapi.entity.ParkingLot;
import com.example.parkingapi.repository.ParkingLotRepository;

import static org.geolatte.geom.builder.DSL.*;
import static org.geolatte.geom.crs.CoordinateReferenceSystems.WGS84;

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

    /*
     * public ParkingLot createNewParkingLot(ParkingLot parkingLot) {
     * return parkingLotRepo.save(parkingLot);
     * }
     */

    public ParkingLot createNewParkingLot() {
        ParkingLot parkingLot1 = new ParkingLot();
        Point<G2D> pnt = point(WGS84, g(4.33, 53.21));
        parkingLot1.setCoordinate(pnt);
        parkingLotRepo.save(parkingLot1);

        return parkingLot1;
    }

}
