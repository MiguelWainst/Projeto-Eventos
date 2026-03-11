package entities;

import java.time.LocalDate;

import entities.enums.TicketType;

public class Show extends Event {

	private Integer durationHours;

	// CONSTRUCTOR
	public Show() {
	}
	public Show(String name, LocalDate date, Integer numberOfTickets, Integer durationHours) {
		super(name, date, numberOfTickets);
		this.durationHours = durationHours;
	}


	// GETTER AND SETTER
	public Integer getDurationHours() {
		return durationHours;
	}

	public void setDurationHours(Integer durationHours) {
		this.durationHours = durationHours;
	}

	// METHOD
	@Override
	public Double price(TicketType ticketType) {
		double price = 0;
		if (ticketType == TicketType.NORMAL) {
			price = numberOfTickets > 1 ? 120 * durationHours : 150 * durationHours; 
		} else if(ticketType == TicketType.STUDENT) price = 100;
		return price;
	}

	@Override
	public void createAndAddTicket(TicketType ticketType) {
		double price = price(ticketType);
		Ticket ticket = new Ticket(ticketType, price);
		super.addTicket(ticket);
	}

}
