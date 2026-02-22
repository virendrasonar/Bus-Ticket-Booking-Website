package com.example.BusTicketApplication;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TicketController {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private BusRepository busRepository;

    private DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/buses/search")
    public String searchBuses(@RequestParam String source,
                              @RequestParam String destination,
                              Model model) {

        List<Bus> buses =
                busRepository.findBySourceAndDestination(source, destination);

        model.addAttribute("buses", buses);
        return "available_buses";
    }

    @GetMapping("/tickets")
    public String listTickets(Model model) {
        model.addAttribute("tickets", ticketRepository.findAll());
        return "list_tickets";
    }

    @GetMapping("/tickets/add")
    public String showAddForm(Model model) {

        model.addAttribute("ticket", new Ticket());

        List<Bus> busList = busRepository.findAll();
        System.out.println("BUS COUNT FROM DB: " + busList.size());

        model.addAttribute("buses", busList);

        return "add_ticket";
    }

    @PostMapping("/tickets/add")
    public String saveTicket(@ModelAttribute Ticket ticket,
                             @RequestParam("busId") Long busId) {

        Bus bus = busRepository.findById(busId).orElse(null);

        if (bus == null) {
            return "redirect:/";
        }

        ticket.setBus(bus);
        ticketRepository.save(ticket);

        return "redirect:/tickets";
    }

    @GetMapping("/tickets/cancel")
    public String showCancelPage(Model model) {
        model.addAttribute("tickets", ticketRepository.findAll());
        return "cancel_ticket";
    }

    @PostMapping("/tickets/cancel/{id}")
    public String cancelTicket(@PathVariable Long id) {
        ticketRepository.deleteById(id);
        return "redirect:/tickets/cancel";
    }

    @GetMapping("/tickets/search")
    public String searchForm() {
        return "search_tickets";
    }

    @PostMapping("/tickets/search")
    public String searchResults(
            @RequestParam(required = false) String passengerName,
            @RequestParam(required = false) String travelDate,
            Model model) {

        List<Ticket> results;

        if (passengerName != null && !passengerName.isBlank()) {
            results = ticketRepository
                    .findByPassengerNameContainingIgnoreCase(passengerName);
        } else if (travelDate != null && !travelDate.isBlank()) {
            LocalDate date = LocalDate.parse(travelDate, formatter);
            results = ticketRepository.findByTravelDate(date);
        } else {
            results = ticketRepository.findAll();
        }

        model.addAttribute("tickets", results);
        return "list_tickets";
    }
}