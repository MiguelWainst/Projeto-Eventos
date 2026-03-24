package entities;

import java.time.LocalDate;

import entities.enums.TheaterType;
import entities.enums.TicketType;

public class Theater extends Event {

	/*
	 * Representa um evento do tipo Theater.
	 *
	 * Responsável por:
	 * - aplicar as regras de preço específicas de teatro
	 * - criar e adicionar tickets ao evento
	 * - informar seu tipo para exibição (getEventLabel)
	 */
	
	// PRICES:
	private static final int NORMAL_CLASSIC_THEATER_PRICE =    80; 	// classic theater price without discount.
	private static final int DISCOUNT_CLASSIC_THEATER_PRICE =  70; 	// classic theater with dicount.
	private static final int NORMAL_MODERN_THEATER_PRICE =     70;	// modern theater without discount.
	private static final int DISCOUNT_MODERN_THEATER_PRICE =   60;	// modern theater with discount.
	private static final int STUDENT_PRICE =                   55;	// student pay same price for every theater ticket

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
	@Override
	public Double price(TicketType ticketType) {
		/*
		 * If a normal person is alone on a theater he will
		 * pay the full price.
		 * 
		 * If a normal person is with more people then they will 
		 * get a discount.
		 * 
		 * Students pay the same price for classic and modern 
		 * theater, dont matter if he's alone or not.
		 * But if a normal person is accompanied with a student
		 * he will get the discount.
		 */
		double totalPrice = 0;

		if (ticketType == TicketType.NORMAL) {
			totalPrice = theaterType == TheaterType.CLASSIC ? (numberOfTickets > 1 ? DISCOUNT_CLASSIC_THEATER_PRICE : NORMAL_CLASSIC_THEATER_PRICE)
					: (numberOfTickets > 1 ? DISCOUNT_MODERN_THEATER_PRICE : NORMAL_MODERN_THEATER_PRICE);
		} else if (ticketType == TicketType.STUDENT) {
			totalPrice = STUDENT_PRICE;
		}

		return totalPrice;
	}

	// Ainda não estudei esse metodo 
	@Override
	public void createAndAddTicket(TicketType ticketType) {
		double price = price(ticketType);
		Ticket ticket = new Ticket(ticketType, price);
		super.addTicket(ticket);
	}
	
	@Override
	public String getEventLabel() {
	    return theaterType.toString();
	}
	
}
