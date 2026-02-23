package com.example.BusTicketApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public void run(String... args) {

        if (busRepository.count() == 0) {

            busRepository.saveAll(List.of(
                    new Bus("Ragestar Travels", "MH12AB1234", "Pune", "Mumbai", 500),
                    new Bus("Sai Travels", "MH14CD5678", "Pune", "Mumbai", 550),
                    new Bus("Volvo Travels", "MH15EF1111", "Pune", "Mumbai", 520),
                    new Bus("Jogeshwari Travels", "MH01XY2222", "Mumbai", "Pune", 500),
                    new Bus("Novelty Travels", "MH02ZZ3333", "Mumbai", "Pune", 530),
                    new Bus("Star Travels", "MH20AA4444", "Pune", "Nashik", 450),
                    new Bus("Royal Travels", "MH21BB5555", "Nashik", "Pune", 460),
                    new Bus("Metro Travels", "MH22CC6666", "Mumbai", "Nagpur", 900),
                    new Bus("Coastal Travels", "GA01DD7777", "Goa", "Mumbai", 800),
                    new Bus("Western Travels", "GA02EE8888", "Goa", "Pune", 700)
            ));
        }

        if (ticketRepository.count() == 0 && busRepository.count() > 0) {

            List<Bus> buses = busRepository.findAll();

            ticketRepository.save(new Ticket("Ramesh Shah", "9876543210", LocalDate.now().plusDays(1), buses.get(0)));
            ticketRepository.save(new Ticket("Tina Mango", "9123456780", LocalDate.now().plusDays(2), buses.get(1)));
            ticketRepository.save(new Ticket("Manish Sapkal", "9988776655", LocalDate.now().plusDays(3), buses.get(2)));
            ticketRepository.save(new Ticket("Suresh Ratna", "9096124590", LocalDate.now().plusDays(1), buses.get(3)));
            ticketRepository.save(new Ticket("Anita Sharma", "8867752488", LocalDate.now().plusDays(4), buses.get(4)));
        }
    }
}