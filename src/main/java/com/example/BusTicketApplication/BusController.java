package com.example.BusTicketApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BusController {

    @Autowired
    private BusRepository busRepository;

    @GetMapping("/search")
    public String searchBuses(@RequestParam String source,
                              @RequestParam String destination,
                              Model model) {

        List<Bus> buses = busRepository
                .findBySourceIgnoreCaseAndDestinationIgnoreCase(source, destination);

        model.addAttribute("buses", buses);
        return "search_tickets";
    }

    @GetMapping("/allbuses")
    public String allBuses(Model model) {
        model.addAttribute("buses", busRepository.findAll());
        return "allbuses";
    }
}