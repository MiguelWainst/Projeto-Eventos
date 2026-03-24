package entities;

import java.time.LocalDate;

import entities.enums.TicketType;

public class Show extends Event {

	/*
	 * Representa um evento do tipo Show.
	 *
	 * Responsável por:
	 * - aplicar as regras de preço específicas de show
	 * - criar e adicionar tickets ao evento
	 * - informar seu tipo para exibição (getEventLabel)
	 */
	
	private Integer durationHours;

	// Constructor
	public Show() {
	}
	public Show(String name, LocalDate date, Integer numberOfTickets, Integer durationHours) {
		super(name, date, numberOfTickets);
		this.durationHours = durationHours;
	}

	// Getters and Setters
	public Integer getDurationHours() {
		return durationHours;
	}

	public void setDurationHours(Integer durationHours) {
		this.durationHours = durationHours;
	}

	// Methods
	@Override
	public Double price(TicketType ticketType) { // Ticket type = Normal or Student type
		/* 
		 * The price depends on how many tickets will be bought.
		 * 1 tickets = $150 per hour
		 * +1 ticket = $120 per hour each
		 * 
		 * But if its a student type it will cost only 120$
		 * independent if de show is longe than 1 hour.
		 */
		double price = 0;
		if (ticketType == TicketType.NORMAL) { // Rule if ticket is normal type.
			price = numberOfTickets > 1 ? 120 * durationHours : 150 * durationHours; 
		} else if(ticketType == TicketType.STUDENT) price = 100; // Rule if its student type.
		return price;
	}

	// Cria um objeto Ticket e adiciona ele na lista do Event.
	@Override
	public void createAndAddTicket(TicketType ticketType) {
		double price = price(ticketType);
		Ticket ticket = new Ticket(ticketType, price);
		super.addTicket(ticket);
	}
	
	@Override
	public String getEventLabel() {
	    return "SHOW";
	}

}
