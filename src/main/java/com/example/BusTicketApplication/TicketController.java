package com.example.BusTicketApplication;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tickets")
public class TicketController {

    private final TicketRepository ticketRepository;
    private final BusRepository busRepository;

    private final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public TicketController(TicketRepository ticketRepository,
                            BusRepository busRepository) {
        this.ticketRepository = ticketRepository;
        this.busRepository = busRepository;
    }

    // HOME PAGE
    @GetMapping("/")
    public String home() {
        return "home";
    }

    // SHOW ALL TICKETS
    @GetMapping
    public String listTickets(Model model) {
        model.addAttribute("tickets", ticketRepository.findAll());
        return "list_tickets";
    }

    // SHOW ADD FORM
    @GetMapping("/add")
    public String showAddForm(@RequestParam(required = false) Long busId,
                              Model model) {

        Ticket ticket = new Ticket();

        if (busId != null) {
            busRepository.findById(busId)
                    .ifPresent(ticket::setBus);
        }

        model.addAttribute("ticket", ticket);
        model.addAttribute("buses", busRepository.findAll());

        return "add_ticket";
    }

    // SAVE TICKET
    @PostMapping("/add")
    public String saveTicket(@ModelAttribute Ticket ticket,
                             @RequestParam("busId") Long busId) {

        return busRepository.findById(busId)
                .map(bus -> {
                    ticket.setBus(bus);
                    ticketRepository.save(ticket);
                    return "redirect:/tickets";
                })
                .orElse("redirect:/");
    }

    // CANCEL PAGE
    @GetMapping("/cancel")
    public String showCancelPage(Model model) {
        model.addAttribute("tickets", ticketRepository.findAll());
        return "cancel_ticket";
    }

    // CANCEL ACTION
    @PostMapping("/cancel/{id}")
    public String cancelTicket(@PathVariable Long id) {
        if (ticketRepository.existsById(id)) {
            ticketRepository.deleteById(id);
        }
        return "redirect:/tickets/cancel";
    }

    // SEARCH FORM
    @GetMapping("/search")
    public String searchForm() {
        return "search_tickets";
    }

    // SEARCH RESULTS
    @PostMapping("/search")
    public String searchResults(
            @RequestParam(required = false) String passengerName,
            @RequestParam(required = false) String travelDate,
            Model model) {

        List<Ticket> results;

        if (passengerName != null && !passengerName.isBlank()) {
            results = ticketRepository
                    .findByPassengerNameContainingIgnoreCase(passengerName);

        } else if (travelDate != null && !travelDate.isBlank()) {
            try {
                LocalDate date = LocalDate.parse(travelDate, formatter);
                results = ticketRepository.findByTravelDate(date);
            } catch (DateTimeParseException e) {
                results = List.of();
            }

        } else {
            results = ticketRepository.findAll();
        }

        model.addAttribute("tickets", results);
        return "list_tickets";
    }
}