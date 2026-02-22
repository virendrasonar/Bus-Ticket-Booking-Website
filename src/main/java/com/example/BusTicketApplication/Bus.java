package com.example.BusTicketApplication;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "buses")
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String busName;
    private String busNumber;
    private String source;
    private String destination;
    private double price;

    private LocalTime departureTime;
    private LocalTime arrivalTime;

    public Bus() {
    }

    // Constructor (optional but good practice)
    public Bus(String busName, String busNumber,
               String source, String destination,
               double price,
               LocalTime departureTime,
               LocalTime arrivalTime) {

        this.busName = busName;
        this.busNumber = busNumber;
        this.source = source;
        this.destination = destination;
        this.price = price;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public Long getId() {
        return id;
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}