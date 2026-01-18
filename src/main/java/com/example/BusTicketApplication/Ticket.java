package com.example.BusTicketApplication;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tickets")
public class Ticket 
{

	@Id
	private Long id;
	private String passengerName;
	private String busNumber;
	private String source;
	private String destination;
	private LocalDate travelDate;
	
	private double price;
	private String whatsappNumber;
	
	public Ticket() 
	{
	}
	
	
	public Ticket(Long id, String passengerName, String busNumber, String source, String destination,
			LocalDate travelDate, double price, String whatsappNumber) {
		this.id = id;
		this.passengerName = passengerName;
		this.busNumber = busNumber;
		this.source = source;
		this.destination = destination;
		this.travelDate = travelDate;
		this.price = price;
		this.whatsappNumber = whatsappNumber;
	}
	
	
	public Long getId() 
	{
		return id;
	}
	public void setId(Long id) 
	{
		this.id = id;
	}
	public String getPassengerName() 
	{
		return passengerName;
	}
	public void setPassengerName(String passengerName) 
	{
		this.passengerName = passengerName;
	}
	public String getBusNumber() 
	{
		return busNumber;
	}
	public void setBusNumber(String busNumber) 
	{
		this.busNumber = busNumber;
	}
	public String getSource()
	{
		return source;
	}
	public void setSource(String source) 
	{
		this.source = source;
	}
	public String getDestination() 
	{
		return destination;
	}
	public void setDestination(String destination) 
	{
		this.destination = destination;
	}
	public LocalDate getTravelDate()
	{
		return travelDate;
	}
	public void setTravelDate(LocalDate travelDate) 
	{
		this.travelDate = travelDate;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getWhatsappNumber() {
		return whatsappNumber;
	}
	public void setWhatsappNumber(String whatsappNumber) {
		this.whatsappNumber = whatsappNumber;
	}
	
	
	

}
