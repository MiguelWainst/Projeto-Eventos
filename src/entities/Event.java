package entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import entities.enums.TicketType;

public abstract class Event {

	private String name;
	private LocalDate date;
	protected Integer numberOfTickets;
	private List<Ticket> tickets = new ArrayList<>();

	// CONSTRUCTOR
	public Event() {
	}

	public Event(String name, LocalDate date, Integer numberOfTickets) {
		super();
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

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Ticket x : tickets) {
			if (x.getPrice() < 50) {
				sb.append(x.getExhibitionType() + " - " + x.getTicketType() + " - " + x.getPrice() + "\n");
			} else if (x.getPrice() <= 80 && x.getPrice() >= 55) {
				sb.append(x.getTheaterType() + " - " + x.getTicketType() + " - " + x.getPrice() + "\n");
			} else if (x.getPrice() >= 100) {
				sb.append(" - " + x.getTicketType() + " - " + x.getPrice() + "\n");
			}
		}
		return sb.toString();
	}

	public abstract Double price(TicketType ticketType); // depends on the event type

	public abstract void createAndAddTicket(TicketType ticketType);

}
