package com.example.BusTicketApplication;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/buses")
public class BusController {

    private final BusRepository busRepository;

    public BusController(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    // Show all buses
    @GetMapping
    public String showAllBuses(Model model) {
        model.addAttribute("buses", busRepository.findAll());
        return "all_buses";
    }

    // Search buses
    @GetMapping("/search")
    public String searchBuses(@RequestParam String source,
                              @RequestParam String destination,
                              Model model) {

        model.addAttribute("buses",
                busRepository.findBySourceIgnoreCaseAndDestinationIgnoreCase(source, destination));

        return "all_buses";
    }
}