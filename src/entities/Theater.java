package entities;

import java.time.LocalDate;

import entities.enums.TheaterType;
import entities.enums.TicketType;

public class Theater extends Event {

	// PRICES:
	private static final int NORMAL_CLASSIC = 80;
	private static final int NORMAL_MODERN = 70;
	private static final int NORMAL_CLASSIC_D = 70; // "D" means "DISCOUNT" (price with the discount)
	private static final int NORMAL_MODERN_D = 60;
	private static final int STUDENT = 55;

	private TheaterType theaterType;

	// CONSTRUCTOR
	public Theater(String name, LocalDate date, Integer numberOfTickets, TheaterType theaterType) {
		super(name, date, numberOfTickets);
		this.theaterType = theaterType;
	}

	// GETTER AND SETTER
	public TheaterType getTheaterType() {
		return theaterType;
	}

	public void setTheaterType(TheaterType theaterType) {
		this.theaterType = theaterType;
	}

	// METHODS
	@Override
	public Double price(TicketType ticketType) {
		double totalPrice = 0;

		if (ticketType == TicketType.NORMAL) {
			totalPrice = theaterType == TheaterType.CLASSIC ? (numberOfTickets > 1 ? NORMAL_CLASSIC_D : NORMAL_CLASSIC)
					: (numberOfTickets > 1 ? NORMAL_MODERN_D : NORMAL_MODERN);
		} else if (ticketType == TicketType.STUDENT) {
			totalPrice = STUDENT;
		}

		return totalPrice;
	}

	@Override
	public void createAndAddTicket(TicketType ticketType) {
		double price = price(ticketType);
		Ticket ticket = new Ticket(theaterType, ticketType, price);
		super.addTicket(ticket);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(theaterType);
		
		return sb.toString();
	}
}
