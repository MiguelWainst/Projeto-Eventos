package application;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

import entities.Event;
import entities.EventSchedule;
import entities.Exhibition;
import entities.Show;
import entities.Theater;
import entities.enums.EventType;
import entities.enums.ExhibitionType;
import entities.enums.TheaterType;
import entities.enums.TicketType;

public class Program {
	
	// Show the prices of a show
	private static String[] show = {
			"PRICES: ", 
			"Normal ticket: $150 (per hour) (1 ticket) or $120 (per hour) each (2+ tickets)",
			"Student price ticket: $100 only (once) each"
	};
	
	/* EXHIBITION
	 * Show the prices of an exhibition:
	 * can be modern exhibition or chinese exhibition
	 */
	private static String[] modernExhibition = {
			"PRICES: ", 
			"Normal ticket: $30 (1 ticket) or $20 each (2+ tickets)",
			"Student price ticket: FREE"
	};
	private static String[] chineseExhibition = {
			"PRICES: ", 
			"Normal ticket: $50 (1 ticket) or $40 each (2+ tickets)",
			"Student price ticket: FREE"	
	};
	
	/* THEATER
	 * Show the prices of a theater:
	 * can be classic or modern
	 */
	private static String[] classicTheater = { 
			"PRICES: ", 
			"Normal ticket: $80 (1 ticket) or $70 each (2+ tickets)",
			"Student price ticket: $60 each" 
	};
	private static String[] modernTheater = { 
			"PRICES: ", 
			"Normal ticket: $70 (1 ticket) or $60 each (2+ tickets)",
			"Student price ticket: $55 each" 
	};

	// Main class
	public static void main(String[] args) {

		// declaration
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		EventSchedule programation = new EventSchedule();
		
		//asks how many events 
		int eventNumber = 0;
		while (true) {
			try {
				System.out.print("How many events? ");
				eventNumber = sc.nextInt();
				break;
			} 
			catch (InputMismatchException e) {
				System.out.println("Error: Invalid input! Try again\n");
				sc.nextLine();
			}
		}
		
		// Input of data
		for (int i = 1; i <= eventNumber; i++) {
			
			// Declara as variaveis fora dos try 
			EventType eventType = null;
			LocalDate date = null;
			Integer numberOfTickets = null;
					
			while (true) {
				try {
					System.out.print("Event type? [THEATER, SHOW, EXHIBITION]: ");
					eventType = EventType.valueOf(sc.next().toUpperCase());
					sc.nextLine();
					break;
				}
				catch(IllegalArgumentException e) {
					System.out.println("Error: Invalid event!\n");
				}
			}

			System.out.print("Event name: ");
			String eventName = sc.nextLine();
			
			while (true) {
				try {
					System.out.print("Event date (DD/MM/YYYY):");
					date = LocalDate.parse(sc.next(), dateFormatter);
					break;
				} 
				catch (DateTimeParseException e) {
					System.out.println("Error: Invalid date format! Try again\n");
				}
			}
			
			while (true) {
				try {
					System.out.print("Number of Tickets: ");
					numberOfTickets = sc.nextInt();
					break;
				} 
				catch (InputMismatchException e) {
					System.out.println("Error: Invalid input! Try again\n");
					sc.nextLine();
				}
			}
			
			System.out.println();

			/*
			 * Call the function responsable for creating a ticket.
			 * 
			 * Se o "eventType" que o usuário digitou for igual o
			 * eventType que a função demanda dentro do seu "if"
			 * então a função vai ser executada
			 * 
			 * Se o eventType for diferente ele entra na função, ele
			 * não passa do if, a função encerra e vai pra proxima
			 * função até achar a correta que encaixe no if da função.
			 */
			createTheater(eventType, eventName, date, sc, programation, numberOfTickets);
			createExhibition(eventType, eventName, date, sc, programation, numberOfTickets);
			createShow(eventType, eventName, date, sc, programation, numberOfTickets);
			
			System.out.println();
		}
		sc.close();
		System.out.println(programation);
		
		String path = "c:\\temp\\Recibo.txt"; 
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
			bw.write("RECIBO:\n\n");
			bw.write(programation.toString());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		sc.close();
	}
	
	public static void createTheater(EventType eventType, String eventName, LocalDate date, Scanner sc,
			EventSchedule programation, Integer numberOfTickets) {
		
		/*
		 * Testa se o "eventType" que chegou da main condiz com o
		 * eventType da função, nesse caso Theater.
		 * Se não for, o if da falso a função encerra e volta pro main.
		 */
		if (eventType == EventType.THEATER) {
			System.out.print("CLASSIC or MODERN? "); // pergunta o TheaterType.
			TheaterType theaterType = TheaterType.valueOf(sc.next().toUpperCase());

			// print the prices and discount system
			System.out.println("");
			if (theaterType == TheaterType.CLASSIC) {
				for (String x : classicTheater) {
					System.out.println(x);
				}
				System.out.println("");
			} else if (theaterType == TheaterType.MODERN) {
				for (String x : modernTheater) {
					System.out.println(x);
				}
				System.out.println("");
			}

			// create one list
			Event theater = new Theater(eventName, date, numberOfTickets, theaterType);

			// create the tickets and add do the list
			for (int j = 0; j < numberOfTickets; j++) {
				System.out.print("NORMAL or STUDENT: "); // Após ser mostrado os preços, ele pega a resposta
				TicketType ticketType = TicketType.valueOf(sc.next().toUpperCase());
				theater.createAndAddTicket(ticketType);
			}
			programation.addEvent(theater);
		}
	}
	
	public static void createExhibition(EventType eventType, String eventName, LocalDate date, Scanner sc,
			EventSchedule programation, Integer numberOfTickets) {
		
		/*
		 * Testa se o "eventType" que chegou da main condiz com o
		 * eventType da função, nesse caso Exhibition.
		 * Se não for, o if da falso a função encerra e volta pro main.
		 */
		if (eventType == EventType.EXHIBITION) {
			System.out.print("CHINESE ART or MODERN ART? "); // Pergunta o ExhibitionType
			ExhibitionType exhibitionType = ExhibitionType.valueOf(sc.next().toUpperCase());
			
			// print the prices and discount system
			if (exhibitionType == ExhibitionType.CHINESE_ART) {
				for (String x : chineseExhibition) {
					System.out.println(x);
				}
				System.out.println("");
			} else if (exhibitionType == ExhibitionType.MODERN_ART) {
				for (String x : modernExhibition) {
					System.out.println(x);
				}
				System.out.println("");
			}
			
			// create one list
			Event exhibition = new Exhibition(eventName, date, numberOfTickets, exhibitionType);

			// create the tickets and add do the list
			for (int j = 0; j < numberOfTickets; j++) {
				System.out.print("NORMAL or STUDENT: "); // Após ser mostrado os preços, ele pega a resposta
				TicketType ticketType = TicketType.valueOf(sc.next().toUpperCase());
				exhibition.createAndAddTicket(ticketType);
			}
			programation.addEvent(exhibition);
		}
	}
	
	public static void createShow(EventType eventType, String eventName, LocalDate date, Scanner sc,
			EventSchedule programation, Integer numberOfTickets) {
		
		/*
		 * Depois de passar por todos os outros if's sobra 
		 * apenas essa opção. Preferi manter o if nessa função
		 * mesmo que ela seja a unica e ultima opção porque se
		 * futuramente for adicionado outro tipo de evento, ja
		 * vai ter estrutura pra criar uma nova função.
		 */
		if (eventType == EventType.SHOW) {
			
			// print the prices and discount system
			for (String x : show) {
				System.out.println(x);
			}
			System.out.println();
			
			System.out.print("How many hours does this show have? ");
			Integer duration = sc.nextInt();

			// create one list
			Event show = new Show(eventName, date, numberOfTickets, duration);

			// create the tickets and add do the list
			for (int j = 0; j < numberOfTickets; j++) {
				System.out.print("NORMAL or STUDENT: "); // Após ser mostrado os preços, ele pega a resposta
				TicketType ticketType = TicketType.valueOf(sc.next().toUpperCase());
				show.createAndAddTicket(ticketType);
			}
			programation.addEvent(show);
		}
	}
}

