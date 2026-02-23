package com.example.BusTicketApplication;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final BusRepository busRepository;
    private final TicketRepository ticketRepository;

    public DataInitializer(BusRepository busRepository,
                           TicketRepository ticketRepository) {
        this.busRepository = busRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public void run(String... args) {

        // Insert only if empty
        if (busRepository.count() == 0) {


            Bus b1 = busRepository.save(new Bus("Sai Travels", "MH12-1001",
                    "Pune", "Mumbai", BigDecimal.valueOf(550),
                    LocalTime.of(7, 0), LocalTime.of(11, 0)));

            Bus b2 = busRepository.save(new Bus("Shree Travels", "MH12-1002",
                    "Pune", "Mumbai", BigDecimal.valueOf(600),
                    LocalTime.of(9, 30), LocalTime.of(13, 30)));

            Bus b3 = busRepository.save(new Bus("Mahalaxmi Travels", "MH12-1003",
                    "Pune", "Mumbai", BigDecimal.valueOf(500),
                    LocalTime.of(18, 0), LocalTime.of(22, 0)));

            Bus b4 = busRepository.save(new Bus("Royal Travels", "MH01-2001",
                    "Mumbai", "Pune", BigDecimal.valueOf(550),
                    LocalTime.of(8, 0), LocalTime.of(12, 0)));

            Bus b5 = busRepository.save(new Bus("Cityline Travels", "MH01-2002",
                    "Mumbai", "Pune", BigDecimal.valueOf(580),
                    LocalTime.of(17, 0), LocalTime.of(21, 0)));

            Bus b6 = busRepository.save(new Bus("Konkan Travels", "MH14-3001",
                    "Pune", "Kolhapur", BigDecimal.valueOf(700),
                    LocalTime.of(6, 0), LocalTime.of(12, 0)));

            Bus b7 = busRepository.save(new Bus("Western Travels", "MH15-4001",
                    "Nashik", "Mumbai", BigDecimal.valueOf(450),
                    LocalTime.of(10, 0), LocalTime.of(14, 0)));

            Bus b8 = busRepository.save(new Bus("Vidarbha Travels", "MH31-5001",
                    "Nagpur", "Pune", BigDecimal.valueOf(1200),
                    LocalTime.of(20, 0), LocalTime.of(7, 0)));

            Bus b9 = busRepository.save(new Bus("Ajanta Travels", "MH20-6001",
                    "Aurangabad", "Pune", BigDecimal.valueOf(800),
                    LocalTime.of(9, 0), LocalTime.of(15, 0)));

            Bus b10 = busRepository.save(new Bus("Goa Travels", "GA01-7001",
                    "Pune", "Goa", BigDecimal.valueOf(900),
                    LocalTime.of(22, 0), LocalTime.of(8, 0)));

            Bus b11 = busRepository.save(new Bus("Beachline Travels", "GA02-7002",
                    "Mumbai", "Goa", BigDecimal.valueOf(1000),
                    LocalTime.of(21, 0), LocalTime.of(9, 0)));

            Bus b12 = busRepository.save(new Bus("Maratha Travels", "MH12-8001",
                    "Kolhapur", "Pune", BigDecimal.valueOf(650),
                    LocalTime.of(7, 30), LocalTime.of(12, 30)));

            Bus b13 = busRepository.save(new Bus("Sahyadri Travels", "MH14-9001",
                    "Pune", "Nashik", BigDecimal.valueOf(480),
                    LocalTime.of(6, 30), LocalTime.of(11, 30)));

            Bus b14 = busRepository.save(new Bus("Metro Travels", "MH01-9002",
                    "Mumbai", "Nashik", BigDecimal.valueOf(470),
                    LocalTime.of(15, 0), LocalTime.of(19, 0)));

            Bus b15 = busRepository.save(new Bus("Coastal Travels", "GA03-9003",
                    "Goa", "Pune", BigDecimal.valueOf(950),
                    LocalTime.of(18, 0), LocalTime.of(6, 0)));

        

            List<Bus> busList = busRepository.findAll();

            String[] names = {
                    "Ramesh", "Radha", "Manish",
                    "Suresh", "Anita", "Vikas"
            };

            Random random = new Random();

            for (int i = 0; i < 6; i++) {

                Ticket ticket = new Ticket();
                ticket.setPassengerName(names[i]);
                ticket.setTravelDate(LocalDate.now().plusDays(random.nextInt(5) + 1));

                // 10-digit random Indian number starting with 9
                ticket.setWhatsappNumber("9" + (int)(Math.random() * 1000000000));

                ticket.setBus(busList.get(random.nextInt(busList.size())));

                ticketRepository.save(ticket);
            }

            System.out.println("Sample buses and tickets inserted successfully.");
        }
    }
}