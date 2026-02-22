package com.example.BusTicketApplication;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByPassengerNameContainingIgnoreCase(String passengerName);

    List<Ticket> findByTravelDate(LocalDate travelDate);

    List<Ticket> findByBus_Id(Long busId);

}