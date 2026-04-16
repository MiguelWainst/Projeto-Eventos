package model.entities;

import model.entities.enums.TheaterType;
import model.entities.enums.TicketType;
import model.services.PriceCalculateService;

import java.time.LocalDate;

public class Theater extends Event {

	/*
	 * Representa um evento do tipo Theater.
	 *
	 * Responsável por:
	 * - criar e adicionar tickets ao evento
	 * - informar o seu tipo para exibição (getEventLabel)
	 */

	private TheaterType theaterType;

	// Constructor
	public Theater(String name, LocalDate date, Integer numberOfTickets, TheaterType theaterType) {
		super(name, date, numberOfTickets);
		this.theaterType = theaterType;
	}

	// Getter and Setter
	public TheaterType getTheaterType() {
		return theaterType;
	}

	public void setTheaterType(TheaterType theaterType) {
		this.theaterType = theaterType;
	}

	// Methods

	/*
	esse method vai usar a classe serviço do PriceCalculateService,
	criar um Ticket e adicionar o ticket na lista de events através
	de um method herdado da classe event.
	 */
	@Override
	public void createAndAddTicket(TicketType ticketType) {
		PriceCalculateService priceCalculateService = new PriceCalculateService();
		double price = priceCalculateService.calculateTheaterPrice(ticketType, theaterType, numberOfTickets);
		Ticket ticket = new Ticket(ticketType, price);
		super.addTicket(ticket);
	}
	
	@Override
	public String getEventLabel() {
	    return theaterType.toString();
	}
	
}
