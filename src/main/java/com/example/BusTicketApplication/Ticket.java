package com.example.BusTicketApplication;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // AUTO GENERATE ID
    private Long id;

    private String passengerName;

    private LocalDate travelDate;

    private String whatsappNumber;

    @ManyToOne
    @JoinColumn(name = "bus_id")   // foreign key column
    private Bus bus;

    public Ticket() {
    }

    public Ticket(String passengerName, LocalDate travelDate,
                  String whatsappNumber, Bus bus) {
        this.passengerName = passengerName;
        this.travelDate = travelDate;
        this.whatsappNumber = whatsappNumber;
        this.bus = bus;
    }

    // Getters & Setters

    public Long getId() {
        return id;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public LocalDate getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(LocalDate travelDate) {
        this.travelDate = travelDate;
    }

    public String getWhatsappNumber() {
        return whatsappNumber;
    }

    public void setWhatsappNumber(String whatsappNumber) {
        this.whatsappNumber = whatsappNumber;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }
}