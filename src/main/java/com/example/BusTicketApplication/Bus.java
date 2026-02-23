package com.example.BusTicketApplication;

import jakarta.persistence.*;

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
    private int price;

    public Bus() {}

    public Bus(String busName, String busNumber,
               String source, String destination, int price) {
        this.busName = busName;
        this.busNumber = busNumber;
        this.source = source;
        this.destination = destination;
        this.price = price;
    }

    public Long getId() { return id; }

    public String getBusName() { return busName; }
    public String getBusNumber() { return busNumber; }
    public String getSource() { return source; }
    public String getDestination() { return destination; }
    public int getPrice() { return price; }
}