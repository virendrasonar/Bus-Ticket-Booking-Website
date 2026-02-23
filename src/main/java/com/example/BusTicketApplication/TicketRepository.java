package com.example.BusTicketApplication;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    // Search by passenger name 
    List<Ticket> findByPassengerNameContainingIgnoreCase(String passengerName);

    // Search by travel date
    List<Ticket> findByTravelDate(LocalDate travelDate);

    // Search by bus id
    List<Ticket> findByBusId(Long busId);

    // Advanced search (name + date)
    List<Ticket> findByPassengerNameContainingIgnoreCaseAndTravelDate(
            String passengerName, LocalDate travelDate);

}