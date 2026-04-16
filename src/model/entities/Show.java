package model.entities;

import java.time.LocalDate;

import model.entities.enums.TicketType;
import model.services.PriceCalculateService;

public class Show extends Event {

	/*
	 * Representa um evento do tipo Show.
	 *
	 * Responsável por:
	 * - criar e adiciona tickets ao evento
	 * - informar o seu tipo para exibição (getEventLabel)
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

	/*
	esse method vai usar a classe serviço do PriceCalculateService,
	criar um Ticket e adicionar o ticket na lista de events através
	de um method herdado da classe event.
	 */
	@Override
	public void createAndAddTicket(TicketType ticketType) {
		PriceCalculateService priceCalculateService = new PriceCalculateService();
		double price = priceCalculateService.calculateShowPrice(ticketType, durationHours, numberOfTickets);
		Ticket ticket = new Ticket(ticketType, price);
		super.addTicket(ticket);
	}

	// Method que informa o seu tipo para o event printar.
	@Override
	public String getEventLabel() {
	    return "SHOW";
	}
}
