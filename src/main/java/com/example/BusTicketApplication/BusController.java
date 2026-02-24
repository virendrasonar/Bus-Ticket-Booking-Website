package com.example.BusTicketApplication;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/buses")
public class BusController {

    private final BusRepository busRepository;

    public BusController(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    // Show all buses -> /buses
    @GetMapping
    public String showAllBuses(Model model) {

        List<Bus> buses = busRepository.findAll();
        model.addAttribute("buses", buses);

        if (buses.isEmpty()) {
            model.addAttribute("message", "No buses available at the moment.");
        }

        return "allbuses"; 
    }

    // Search buses -> /buses/search
    @GetMapping("/search")
    public String searchBuses(@RequestParam String source,
                              @RequestParam String destination,
                              Model model) {

        source = source.trim();
        destination = destination.trim();

        List<Bus> buses =
                busRepository.findBySourceIgnoreCaseAndDestinationIgnoreCase(source, destination);

        model.addAttribute("buses", buses);

        if (buses.isEmpty()) {
            model.addAttribute("message",
                    "No buses found for " + source + " → " + destination);
        }

        return "available_buses";   
    }
}