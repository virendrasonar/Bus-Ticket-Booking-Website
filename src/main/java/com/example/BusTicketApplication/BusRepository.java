package com.example.BusTicketApplication;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusRepository extends JpaRepository<Bus, Long> {

    
    List<Bus> findBySourceIgnoreCaseAndDestinationIgnoreCase(String source, String destination);

}