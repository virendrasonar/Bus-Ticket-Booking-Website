package com.example.BusTicketApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BusController {

    @Autowired
    private BusRepository busRepository;

    @GetMapping("/buses")
    public String showAllBuses(Model model) {
        model.addAttribute("buses", busRepository.findAll());
        return "all_buses";
    }

    @GetMapping("/buses/search")
    public String searchBuses(@RequestParam String source,
                              @RequestParam String destination,
                              Model model) {

        model.addAttribute("buses",
                busRepository.findBySourceIgnoreCaseAndDestinationIgnoreCase(source, destination));

        return "all_buses";
    }
}