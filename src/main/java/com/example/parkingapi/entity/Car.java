package com.example.parkingapi.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reg;

    @ManyToOne // (cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    @JsonBackReference
    private Person person;

    @OneToMany(mappedBy = "car", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<ParkingEvent> parkingEvent = new HashSet<>();

    public Car() {
    }

    public Set<ParkingEvent> getParkingEvent() {
        return parkingEvent;
    }

    public void setParkingEvent(Set<ParkingEvent> parkingEvent) {
        this.parkingEvent = parkingEvent;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getReg() {
        return reg;
    }

    public void setReg(String reg) {
        this.reg = reg;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
