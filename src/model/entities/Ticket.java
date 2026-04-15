package model.entities;

import model.entities.enums.TicketType;

public class Ticket {
	
	/*
	 * Representa um ingresso individual de um evento.
	 *
	 * Responsável apenas por armazenar:
	 * - tipo do ingresso (NORMAL ou STUDENT)
	 * - preço já calculado
	 *
	 * Não possui regras de negócio.
	 * Não sabe qual é o tipo de evento.
	 *
	 * Funciona como um objeto de dados simples,
	 * sendo criado pelos eventos após o cálculo do preço.
	 */

	private Double price;
	
	private TicketType ticketType;
	
	public Ticket(TicketType ticketType, Double price) {
		this.ticketType = ticketType;
		this.price = price;
	}
	
	
	// GETTER AND SETTER
	public TicketType getTicketType() {
		return ticketType;
	}
	
	public Double getPrice() {
		return price;
	}
	
	public void setTicketType(TicketType ticketType) {
		this.ticketType = ticketType;
	}
	
	@Override
	public String toString() {
		return ticketType + " - " + price;
	}
}
