package entities;

import java.time.LocalDate;

import entities.enums.ExhibitionType;
import entities.enums.TicketType;

public class Exhibition extends Event {

	/*
	 * Representa um evento do tipo Exhibition.
	 *
	 * Responsável por:
	 * - aplicar as regras de preço específicas de exposição
	 * - criar e adicionar tickets ao evento
	 * - informar seu tipo para exibição (getEventLabel)
	 */
	
	private static final int NORMAL_CHINESE =           50;
	private static final int NORMAL_CHINESE_DISCOUNT =  40; //"D" means "DISCOUNT" (price with the discount)
	private static final int NORMAL_MODERN =            30;
	private static final int NORMAL_MODERN_DISCOUNT =   20;
	private static final int STUDENT =                  0;
	
	private ExhibitionType exhibitionType;

	// Constructors
	public Exhibition() {
	}
	
	public Exhibition(String name, LocalDate date, Integer numberOfTickets, ExhibitionType exhibitionType) {
		super(name, date, numberOfTickets);
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
	@Override
	public Double price(TicketType ticketType) {
		/* 
		 * The price depends on how many tickets will be bought.
		 * And what type of exhibition is:
		 * 
		 * 1 ticket of chinese art = $50
		 * +1 ticket = $40 each
		 * 
		 * 1 ticket of modern art = $30
		 * +1 ticket = 20$
		 * 
		 * But if its a student type it will be free acess for
		 * both type of exhibition.
		 */
		double price = 0.0;
		if (exhibitionType == ExhibitionType.CHINESE_ART) {
			price = ticketType == TicketType.NORMAL ? (numberOfTickets > 1 ? NORMAL_CHINESE_DISCOUNT : NORMAL_CHINESE)
					: (price = STUDENT);
		} else if (exhibitionType == ExhibitionType.MODERN_ART) {
			price = ticketType == TicketType.NORMAL ? (numberOfTickets > 1 ? NORMAL_MODERN_DISCOUNT : NORMAL_MODERN)
					: (price = STUDENT);
		}
		return price;
	}

	@Override
	public void createAndAddTicket(TicketType ticketType) {
		double price = price(ticketType);
		Ticket ticket = new Ticket(ticketType, price);
		super.addTicket(ticket);
	}
	
	@Override
	public String getEventLabel() {
	    return exhibitionType.toString();
	}

}
