package application;


import model.entities.EventSchedule;
import model.entities.enums.EventType;
import model.interfaces.IPriceDisplayService;
import model.interfaces.IPriceService;
import model.services.EventService;
import model.services.PriceCalculateService;
import model.services.PriceDisplayService;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Program {
	
	// Main class
	public static void main(String[] args) {

		// declaration
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		EventSchedule programation = new EventSchedule();
		
		// Pergunta quantos eventos serão efetuados
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
		
		// Entrada de dados pelo usuário
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
					date = LocalDate.parse("10/10/2000", dateFormatter);
					//date = LocalDate.parse(sc.next(), dateFormatter);
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
			// fim da entrada de dados

			System.out.println();

			/*
			 * Call the function responsible for creating a ticket.
			 * 
			 * Se o "eventType" que o usuário digitou for igual o
			 * eventType que a função demanda dentro do seu "if"
			 * então a função vai ser executada
			 */

			IPriceDisplayService priceDisplayService = new PriceDisplayService();
			IPriceService priceService = new PriceCalculateService();
			EventService eventService = new EventService(priceDisplayService, priceService);
			if (eventType == EventType.THEATER) {
			    eventService.createTheater(eventType, eventName, date, sc, programation, numberOfTickets);
			}
			else if (eventType == EventType.SHOW) {
			    eventService.createShow(eventType, eventName, date, sc, programation, numberOfTickets);
			}
			else if (eventType == EventType.EXHIBITION) {
			    eventService.createExhibition(eventType, eventName, date, sc, programation, numberOfTickets);
			}
			
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
	}
	
}

