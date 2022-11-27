package com.example.parkingapi.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int REG;

    @ManyToOne
    private Person owners;

    @OneToMany(mappedBy = "car")
    private Set<ParkingEvent> parkingEvent = new HashSet<>();

    public Set<ParkingEvent> getParkingEvent() {
        return parkingEvent;
    }

    public void setParkingEvent(Set<ParkingEvent> parkingEvent) {
        this.parkingEvent = parkingEvent;
    }

    public Person getOwners() {
        return owners;
    }

    public void setOwners(Person owners) {
        this.owners = owners;
    }

    public int getREG() {
        return REG;
    }

    public void setREG(int rEG) {
        REG = rEG;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
