package entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import entities.enums.TicketType;

public abstract class Event {

	/*
	 * Classe base que representa um evento genérico.
	 *
	 * Responsável por:
	 * - armazenar dados comuns (nome do evento, data do evento, 
	 *   quantidade de ingressos pro evento)
	 * - manter a lista de tickets do evento
	 * - definir o contrato de cálculo de preço (price)
	 * - definir a criação de tickets (createAndAddTicket)
	 * - fornecer a estrutura de exibição (toString)
	 *
	 * Não sabe regras específicas de preço, pois isso é responsabilidade
	 * das subclasses (Show, Theater, Exhibition).
	 *
	 * Aplica polimorfismo: cada tipo de evento implementa sua própria lógica.
	 */
	
	private String     name;
	private LocalDate  date;
	protected Integer  numberOfTickets;
	
	private List<Ticket> tickets = new ArrayList<>();

	// CONSTRUCTOR
	public Event() {
	}

	public Event(String name, LocalDate date, Integer numberOfTickets) {
		this.name = name;
		this.date = date;
		this.numberOfTickets = numberOfTickets;
	}

	// GETTERS AND SETTERS
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public Integer getNumberOfTickets() {
		return numberOfTickets;
	}

	public void setNumberOfTickets(Integer numberOfTickets) {
		this.numberOfTickets = numberOfTickets;
	}

	// METHODS
	public void addTicket(Ticket ticket) {
		tickets.add(ticket);
	}
	
	public Double getTotalPrice() {
		Double sum = 0.0;
		for (Ticket x : tickets) {
			sum += x.getPrice();
		}
		return sum;
	}

	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();

	    for (Ticket t : tickets) {
	        sb.append(getEventLabel())
	          .append(" - ")
	          .append(t.getTicketType())
	          .append(" - ")
	          .append(t.getPrice())
	          .append("\n");
	    }

	    return sb.toString();
	}

	public abstract Double price(TicketType ticketType); // depends on the event type

	public abstract void createAndAddTicket(TicketType ticketType);
	
	public abstract String getEventLabel();

}
