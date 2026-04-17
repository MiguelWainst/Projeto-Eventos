package model.entities;

import model.entities.enums.ExhibitionType;
import model.entities.enums.TicketType;
import model.interfaces.IPriceService;

import java.time.LocalDate;

public class Exhibition extends Event {

	/*
	 * Representa um evento do tipo Exhibition.
	 *
	 * Responsável por:
	 * - criar e adicionar tickets ao evento
	 * - informar o seu tipo para exibição (getEventLabel)
	 */

	private final IPriceService priceService;
	private ExhibitionType exhibitionType;

	// Constructors
	public Exhibition(String name, LocalDate date, Integer numberOfTickets, IPriceService priceService, ExhibitionType exhibitionType) {
		super(name, date, numberOfTickets);
        this.priceService = priceService;
        this.exhibitionType = exhibitionType;
	}

	// Getters and Setters
	public ExhibitionType getExhibitionType() {
		return exhibitionType;
	}
	public void setExhibitionType(ExhibitionType exhibitionType) {
		this.exhibitionType = exhibitionType;
	}

	// METHODS

	/*
	esse method vai usar a classe serviço do PriceCalculateService,
	criar um Ticket e adicionar o ticket na lista de events através
	de um method herdado da classe event.
	 */
	@Override
	public void createAndAddTicket(TicketType ticketType) {
		double price = priceService.calculateExhibitionPrice(ticketType, numberOfTickets, exhibitionType);
		Ticket ticket = new Ticket(ticketType, price);
		super.addTicket(ticket);
	}
	
	@Override
	public String getEventLabel() {
	    return exhibitionType.toString();
	}

}
