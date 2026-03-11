package entities;

import java.time.LocalDate;

import entities.enums.ExhibitionType;
import entities.enums.TicketType;

public class Exhibition extends Event {

	private static final int NORMAL_CHINESE = 50;
	private static final int NORMAL_CHINESED = 40; //"D" means "DISCOUNT" (price with the discount)
	private static final int NORMAL_MODERN = 30;
	private static final int NORMAL_MODERND = 20;
	private static final int STUDENT = 0;
	
	
	private ExhibitionType exhibitionType;

	// CONSTRUCTORS
	public Exhibition() {
	}
	public Exhibition(String name, LocalDate date, Integer numberOfTickets, ExhibitionType exhibitionType) {
		super(name, date, numberOfTickets);
		this.exhibitionType = exhibitionType;
	}


	// GETTER AND SETTER
	public ExhibitionType getExhibitionType() {
		return exhibitionType;
	}

	public void setExhibitionType(ExhibitionType exhibitionType) {
		this.exhibitionType = exhibitionType;
	}

	// METHODS
	@Override
	public Double price(TicketType ticketType) {
		double price = 0.0;
		if (exhibitionType == ExhibitionType.CHINESE_ART) {
			price = ticketType == TicketType.NORMAL ? (numberOfTickets > 1 ? NORMAL_CHINESED : NORMAL_CHINESE)
					: (price = STUDENT);
		} else if (exhibitionType == ExhibitionType.MODERN_ART) {
			price = ticketType == TicketType.STUDENT ? (numberOfTickets > 1 ? NORMAL_MODERND : NORMAL_MODERN)
					: (price = STUDENT);
		}
		return price;
	}

	@Override
	public void createAndAddTicket(TicketType ticketType) {
		double price = price(ticketType);
		Ticket ticket = new Ticket(exhibitionType, ticketType, price);
		super.addTicket(ticket);
	}

}
