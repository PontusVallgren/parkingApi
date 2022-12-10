package com.example.parkingapi.entity;

import java.util.List;

import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class ParkingLot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Point<G2D> coordinate;

    @JsonIgnore
    @OneToMany(mappedBy = "parkingLot", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<ParkingEvent> parkingEvent;

    public ParkingLot() {
    }

    public Point<G2D> getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Point<G2D> coordinate) {
        this.coordinate = coordinate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ParkingEvent> getParkingEvent() {
        return parkingEvent;
    }

    public void setParkingEvent(List<ParkingEvent> parkingEvent) {
        this.parkingEvent = parkingEvent;
    }
}
