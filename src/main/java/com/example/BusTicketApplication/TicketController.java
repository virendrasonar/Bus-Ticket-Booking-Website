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

    // SEARCH BUSES (Home search bar)
    @GetMapping("/buses/search")
    public String searchBuses(@RequestParam String source,
                              @RequestParam String destination,
                              Model model) {

        List<Bus> buses =
                busRepository.findBySourceIgnoreCaseAndDestinationIgnoreCase(source, destination);

        model.addAttribute("buses", buses);
        return "available_buses";
    }

    // SHOW ALL TICKETS
    @GetMapping("/tickets")
    public String listTickets(Model model) {
        model.addAttribute("tickets", ticketRepository.findAll());
        return "list_tickets";
    }

    // SHOW ADD TICKET PAGE (WITH OPTIONAL BUS PRESELECT)
    @GetMapping("/tickets/add")
    public String showAddForm(@RequestParam(required = false) Long busId,
                              Model model) {

        Ticket ticket = new Ticket();

        if (busId != null) {
            Bus bus = busRepository.findById(busId).orElse(null);
            ticket.setBus(bus);
        }

        model.addAttribute("ticket", ticket);
        model.addAttribute("buses", busRepository.findAll());

        return "add_ticket";
    }

    // SAVE TICKET
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

    // CANCEL PAGE
    @GetMapping("/tickets/cancel")
    public String showCancelPage(Model model) {
        model.addAttribute("tickets", ticketRepository.findAll());
        return "cancel_ticket";
    }

    // CANCEL ACTION
    @PostMapping("/tickets/cancel/{id}")
    public String cancelTicket(@PathVariable Long id) {
        ticketRepository.deleteById(id);
        return "redirect:/tickets/cancel";
    }

    // SEARCH TICKET PAGE
    @GetMapping("/tickets/search")
    public String searchForm() {
        return "search_tickets";
    }

    // SEARCH RESULTS
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