package application;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import entities.Event;
import entities.Exhibition;
import entities.Programation;
import entities.Show;
import entities.Theater;
import entities.enums.EventType;
import entities.enums.ExhibitionType;
import entities.enums.TheaterType;
import entities.enums.TicketType;

public class Program {
	//SHOW
	private static String[] SHOW = {
			"PRICES: ", 
			"Normal ticket: $150 (per hour) (1 ticket) or $120 (per hour) each (2+ tickets)",
			"Student price ticket: $100 only (once) each"
	};
	
	//EXHIBITION
	private static String[] MODERNE = {
			"PRICES: ", 
			"Normal ticket: $30 (1 ticket) or $20 each (2+ tickets)",
			"Student price ticket: FREE"
	};
	private static String[] CHINESE = {
			"PRICES: ", 
			"Normal ticket: $50 (1 ticket) or $40 each (2+ tickets)",
			"Student price ticket: FREE"	
	};
	
	//THEATER
	private static String[] CLASSICT = { 
			"PRICES: ", 
			"Normal ticket: $80 (1 ticket) or $70 each (2+ tickets)",
			"Student price ticket: $60 each" 
	};
	private static String[] MODERNT = { 
			"PRICES: ", 
			"Normal ticket: $70 (1 ticket) or $60 each (2+ tickets)",
			"Student price ticket: $55 each" 
	};

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		Programation programation = new Programation();

		System.out.print("How many events? ");
		int eventNumber = sc.nextInt();

		for (int i = 1; i <= eventNumber; i++) {
			System.out.print("Event type? [THEATER, SHOW, EXHIBITION]: ");
			EventType eventType = EventType.valueOf(sc.next().toUpperCase());
			sc.nextLine();

			System.out.print("Event name: ");
			String eventName = sc.nextLine();

			System.out.print("Event date (DD/MM):");
			LocalDate date = LocalDate.parse(sc.next(), fmt1);

			System.out.print("Number of Tickets: ");
			Integer numberOfTickets = sc.nextInt();

			System.out.println();

			IF_THEATER(eventType, eventName, date, sc, programation, numberOfTickets);
			IF_EXHIBITION(eventType, eventName, date, sc, programation, numberOfTickets);
			IF_SHOW(eventType, eventName, date, sc, programation, numberOfTickets);
			
			System.out.println();
		}
		System.out.println(programation);

	}
	
	public static void IF_THEATER(EventType eventType, String eventName, LocalDate date, Scanner sc,
			Programation programation, Integer numberOfTickets) {

		if (eventType == EventType.THEATER) {
			System.out.print("CLASSIC or MODERN? ");
			TheaterType theaterType = TheaterType.valueOf(sc.next().toUpperCase());

			// print the prices and discount system
			System.out.println("");
			if (theaterType == TheaterType.CLASSIC) {
				for (String x : CLASSICT) {
					System.out.println(x);
				}
				System.out.println("");
			} else if (theaterType == TheaterType.MODERN) {
				for (String x : MODERNT) {
					System.out.println(x);
				}
				System.out.println("");
			}

			// create one list
			Event theater = new Theater(eventName, date, numberOfTickets, theaterType);

			// create the tickets and add do the list
			for (int j = 0; j < numberOfTickets; j++) {
				System.out.print("NORMAL or STUDENT: ");
				TicketType ticketType = TicketType.valueOf(sc.next().toUpperCase());
				theater.createAndAddTicket(ticketType);
			}
			programation.addEvent(theater);
		}
	}
	
	public static void IF_EXHIBITION(EventType eventType, String eventName, LocalDate date, Scanner sc,
			Programation programation, Integer numberOfTickets) {
		
		if (eventType == EventType.EXHIBITION) {
			System.out.print("CHINESE ART or MODERN ART? ");
			ExhibitionType exhibitionType = ExhibitionType.valueOf(sc.next().toUpperCase());
			
			if (exhibitionType == ExhibitionType.CHINESE_ART) {
				for (String x : CHINESE) {
					System.out.println(x);
				}
				System.out.println("");
			} else if (exhibitionType == ExhibitionType.MODERN_ART) {
				for (String x : MODERNE) {
					System.out.println(x);
				}
				System.out.println("");
			}
			// create one list
			Event exhibition = new Exhibition(eventName, date, numberOfTickets, exhibitionType);

			// create the tickets and add do the list
			for (int j = 0; j < numberOfTickets; j++) {
				System.out.print("NORMAL or STUDENT: ");
				TicketType ticketType = TicketType.valueOf(sc.next().toUpperCase());
				exhibition.createAndAddTicket(ticketType);
			}
			programation.addEvent(exhibition);
		}
	}
	
	public static void IF_SHOW(EventType eventType, String eventName, LocalDate date, Scanner sc,
			Programation programation, Integer numberOfTickets) {
		if (eventType == EventType.SHOW) {
			for (String x : SHOW) {
				System.out.println(x);
			}
			System.out.println();
			
			System.out.print("How many hours does this show have? ");
			Integer duration = sc.nextInt();

			// create one list
			Event show = new Show(eventName, date, numberOfTickets, duration);

			// create the tickets and add do the list
			for (int j = 0; j < numberOfTickets; j++) {
				System.out.print("NORMAL or STUDENT: ");
				TicketType ticketType = TicketType.valueOf(sc.next().toUpperCase());
				show.createAndAddTicket(ticketType);
			}
			programation.addEvent(show);

		}

	}
	
}

