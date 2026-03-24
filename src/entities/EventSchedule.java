package entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventSchedule {

	private List<Event> events = new ArrayList<>();

	// GETTER
	public List<Event> getEvents() {
		return events;
	}

	// METHODS
	public void addEvent(Event event) {
		events.add(event);
	}
	
	public Double getTotalPrice() {
	    Double sum = 0.0;
	    for (Event x : events) {
	        sum += x.getTotalPrice();
	    }
	    return sum;
	}
	
	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();

	    for (Event x : events) {
	        sb.append("EVENT NAME\n");
	        sb.append(x.getName()).append("\n");

	        sb.append("DATES: ");
	        sb.append("Today's day: ").append(LocalDate.now());
	        sb.append(" | Event's date: ").append(x.getDate()).append("\n");

	        sb.append("NUMBER OF TICKETS: ");
	        sb.append(x.getNumberOfTickets()).append("\n");

	        sb.append("TICKETS:\n");
	        for (Ticket t : x.getTickets()) {
	            sb.append(" - ").append(t.toString()).append("\n");
	        }

	        sb.append("\n");
	    }

	    sb.append("TOTAL PRICE OF ALL EVENTS: $").append(getTotalPrice());

	    return sb.toString();
	}
}
