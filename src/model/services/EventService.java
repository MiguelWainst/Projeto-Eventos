package model.services;

import model.entities.*;
import model.entities.enums.EventType;
import model.entities.enums.ExhibitionType;
import model.entities.enums.TheaterType;
import model.entities.enums.TicketType;
import model.interfaces.IPriceDisplayService;
import model.interfaces.IPriceService;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class EventService {
	
	/*
	 * Classe responsável por prestar um serviço de escolha.
	 * As funções dessa classe vão pegar as informações e 
	 * trabalhar em cima delas.
	 */

	private final IPriceDisplayService priceDisplayService;
	private final IPriceService priceService;

	// construtor
	public EventService(IPriceDisplayService priceDisplayService, IPriceService priceService) {
		this.priceDisplayService = priceDisplayService;
        this.priceService = priceService;
    }

	public void createTheater(EventType eventType, String eventName, LocalDate date, Scanner sc,
	                          EventSchedule programation, Integer numberOfTickets) {

		System.out.print("CLASSIC or MODERN? "); // pergunta o TheaterType.
		TheaterType theaterType = null;
		// While é pra caso o usuário erre o input
		while (true) {
			try {
				theaterType = TheaterType.valueOf(sc.next().toUpperCase());
				sc.nextLine();
				break;
			}
			catch (IllegalArgumentException e) {
				System.out.print("This theater type is invalid, try again: ");
			}
		}

		// print the prices and discount system
		System.out.println(priceDisplayService.getTheaterPriceDescription(theaterType));

		// create one list
		Event theater = new Theater(eventName, date, numberOfTickets, priceService, theaterType);

		// create the tickets and add do the list
		createTicket(numberOfTickets, sc, theater);
		programation.addEvent(theater);
	}
	
	public void createExhibition(EventType eventType, String eventName, LocalDate date, Scanner sc,
			EventSchedule programation, Integer numberOfTickets) {
		
		System.out.print("CHINESE ART or MODERN ART? "); // Pergunta o ExhibitionType
		ExhibitionType exhibitionType = null;
		// While é pra caso o usuário erre o input
		while (true) {
			try {
				exhibitionType = ExhibitionType.valueOf(sc.next().toUpperCase());
				sc.nextLine();
				break;
			}
			catch (IllegalArgumentException e) {
				System.out.print("This exhibition type is invalid, try again: ");
			}
		}

		// print the prices and discount system
		System.out.println(priceDisplayService.getExhibitionPriceDescription(exhibitionType));

		// create one list
		Event exhibition = new Exhibition(eventName, date, numberOfTickets, priceService, exhibitionType);

		// create the tickets and add do the list
		createTicket(numberOfTickets, sc, exhibition);
		programation.addEvent(exhibition);
	}
	
	public void createShow(EventType eventType, String eventName, LocalDate date, Scanner sc,
			EventSchedule programation, Integer numberOfTickets) {
		
		// print the prices and discount system
		System.out.println(priceDisplayService.getShowPriceDescription());
		System.out.println();

		System.out.print("How many hours does this show have? ");
		Integer duration;
		// While é pra caso o usuário erre o input
		while (true) {
			try {
				duration = sc.nextInt();
				break;
			}
			catch (InputMismatchException e) {
				System.out.print("Invalid format, try again: ");
				sc.nextLine();
			}
		}

		// create one list
		Event show = new Show(eventName, date, numberOfTickets, priceService, duration);

		// create the tickets and add do the list
		createTicket(numberOfTickets, sc, show);
		programation.addEvent(show);
	}

	private void createTicket(Integer numberOfTickets, Scanner sc, Event event) {
		for (int j = 0; j < numberOfTickets; j++) {
			System.out.print("NORMAL or STUDENT: "); // Após ser mostrado os preços, ele pega a resposta
			TicketType ticketType;
			// While é pra caso o usuário erre o input
			while (true) {
				try {
					ticketType = TicketType.valueOf(sc.next().toUpperCase());
					sc.nextLine();
					break;
				}
				catch (IllegalArgumentException e) {
					System.out.print("Invalid type of ticket, try again: ");
				}
			}
			event.createAndAddTicket(ticketType);
		}
	}

}

