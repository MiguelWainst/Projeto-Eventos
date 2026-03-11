package entities;

import entities.enums.ExhibitionType;
import entities.enums.TheaterType;
import entities.enums.TicketType;

public class Ticket {

	private ExhibitionType exhibitionType;
	private TheaterType theaterType;
	private TicketType ticketType;
	private Double price;

	public Ticket(TicketType ticketType, Double price) {
		super();
		this.ticketType = ticketType;
		this.price = price;
	}
	public Ticket(TheaterType theaterType, TicketType ticketType, Double price) {
		super();
		this.theaterType = theaterType;
		this.ticketType = ticketType;
		this.price = price;
	}
	public Ticket(ExhibitionType exhibitionType, TicketType ticketType, Double price) {
		super();
		this.exhibitionType = exhibitionType;
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
	public ExhibitionType getExhibitionType() {
		return exhibitionType;
	}
	public void setExhibitionType(ExhibitionType exhibitionType) {
		this.exhibitionType = exhibitionType;
	}
	public TheaterType getTheaterType() {
		return theaterType;
	}
	public void setTheaterType(TheaterType theaterType) {
		this.theaterType = theaterType;
	}
	public void setTicketType(TicketType ticketType) {
		this.ticketType = ticketType;
	}
	
	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();

	    if (price < 50) {
	        sb.append(exhibitionType + " - " + ticketType + " - " + price);
	    } else if (price <= 80 && price >= 55) {
	        sb.append(theaterType + " - " + ticketType + " - " + price);
	    } else if (price > 100) {
	        sb.append("SHOW - " + ticketType + " - " + price);
	    }

	    return sb.toString();
	}
}
