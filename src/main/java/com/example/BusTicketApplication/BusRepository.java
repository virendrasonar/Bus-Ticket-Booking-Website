package com.example.BusTicketApplication;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusRepository extends JpaRepository<Bus, Long> {

    // Exact match search
    List<Bus> findBySourceAndDestination(String source, String destination);

    // Case insensitive search 
    List<Bus> findBySourceIgnoreCaseAndDestinationIgnoreCase(String source, String destination);

}