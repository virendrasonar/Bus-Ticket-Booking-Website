package com.example.BusTicketApplication;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TicketController 
{
	@Autowired
	private TicketRepository ticketRepository;
	
	private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	@GetMapping("/")
	public  String home()
	{
		return "home"; //loads home.html
	}
	
	@GetMapping("/tickets")
	public String listTickets(Model model)
	{
		model.addAttribute("tickets", ticketRepository.findAll());
		return "list_tickets";
	}
	

	@GetMapping("/tickets/add")
	public String showAddForm(Model model)
	{
	    model.addAttribute("ticket", new Ticket()); // singular
	    return "add_ticket";
	}

	@PostMapping("/tickets/add")
	public String saveTicket(@ModelAttribute Ticket ticket)
	{
		ticketRepository.save(ticket);
		return "redirect:/tickets";
	}
	
	@GetMapping("/tickets/delete/{id}")
	public String deleteTicket(@PathVariable Long id)
	{
		ticketRepository.deleteById(id);
		return "redirect:/tickets";
		
	}
	
	@PostMapping("/tickets/cancel/{id}")
	public String cancelTicket(@PathVariable Long id)
	{
	    ticketRepository.deleteById(id);
	    return "redirect:/tickets/cancel";
	}
	
	@GetMapping("/tickets/cancel")
	public String showCancelPage(Model model)
	{
		model.addAttribute("tickets", ticketRepository.findAll());
		return "cancel_ticket";
	}
	
	@GetMapping("/tickets/search")
	public String searchForm()
	{
		return "search_tickets";
	}
	
	@PostMapping("/tickets/search")
	public String searchResults(@RequestParam(required=false) String passengerName,
			                    @RequestParam(required=false) String travelDate, 
			                    Model model)
	{
		List<Ticket>results;
		if (passengerName!=null && !passengerName.isBlank())
		{
			results=ticketRepository.findByPassengerNameContainingIgnoreCase(passengerName);
		}
		
		else if(travelDate != null && !travelDate.isBlank())
		{
			LocalDate date=LocalDate.parse(travelDate, dateFormatter);
			results=ticketRepository.findByTravelDate(date);
		}
		else
		{
			results=ticketRepository.findAll();
		}
		
		model.addAttribute("tickets", results);
		return "list_tickets";
		
	}
}
