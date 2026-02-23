package com.example.BusTicketApplication;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String passengerName;

    @Column(nullable = false)
    private LocalDate travelDate;

    @Column(nullable = false, length = 15)
    private String whatsappNumber;

    // IMPORTANT: Use EAGER to avoid 500 error in Thymeleaf
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bus_id", nullable = false)
    private Bus bus;

    public Ticket() {}

    // Constructor for DataInitializer
    public Ticket(String passengerName, String whatsappNumber,
                  LocalDate travelDate, Bus bus) {
        this.passengerName = passengerName;
        this.whatsappNumber = whatsappNumber;
        this.travelDate = travelDate;
        this.bus = bus;
    }

    public Long getId() { return id; }

    public String getPassengerName() { return passengerName; }
    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public LocalDate getTravelDate() { return travelDate; }
    public void setTravelDate(LocalDate travelDate) {
        this.travelDate = travelDate;
    }

    public String getWhatsappNumber() { return whatsappNumber; }
    public void setWhatsappNumber(String whatsappNumber) {
        this.whatsappNumber = whatsappNumber;
    }

    public Bus getBus() { return bus; }
    public void setBus(Bus bus) { this.bus = bus; }
}