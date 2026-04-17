package model.services;

import model.entities.*;
import model.entities.enums.EventType;
import model.entities.enums.ExhibitionType;
import model.entities.enums.TheaterType;
import model.entities.enums.TicketType;
import model.interfaces.IPriceDisplayService;
import model.interfaces.IPriceService;

import java.time.LocalDate;
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
		TheaterType theaterType = TheaterType.valueOf(sc.next().toUpperCase());

		// print the prices and discount system
		System.out.println(priceDisplayService.getTheaterPriceDescription(theaterType));

		// create one list
		Event theater = new Theater(eventName, date, numberOfTickets, priceService, theaterType);

		// create the tickets and add do the list
		for (int j = 0; j < numberOfTickets; j++) {
			System.out.print("NORMAL or STUDENT: "); // Após ser mostrado os preços, ele pega a resposta
			TicketType ticketType = TicketType.valueOf(sc.next().toUpperCase());
			theater.createAndAddTicket(ticketType);
		}
		programation.addEvent(theater);

	}
	
	public void createExhibition(EventType eventType, String eventName, LocalDate date, Scanner sc,
			EventSchedule programation, Integer numberOfTickets) {
		
		System.out.print("CHINESE ART or MODERN ART? "); // Pergunta o ExhibitionType
		ExhibitionType exhibitionType = ExhibitionType.valueOf(sc.next().toUpperCase());

		// print the prices and discount system
		System.out.println(priceDisplayService.getExhibitionPriceDescription(exhibitionType));

		// create one list
		Event exhibition = new Exhibition(eventName, date, numberOfTickets, priceService, exhibitionType);

		// create the tickets and add do the list
		for (int j = 0; j < numberOfTickets; j++) {
			System.out.print("NORMAL or STUDENT: "); // Após ser mostrado os preços, ele pega a resposta
			TicketType ticketType = TicketType.valueOf(sc.next().toUpperCase());
			exhibition.createAndAddTicket(ticketType);
		}
		programation.addEvent(exhibition);

	}
	
	public void createShow(EventType eventType, String eventName, LocalDate date, Scanner sc,
			EventSchedule programation, Integer numberOfTickets) {
		
		// print the prices and discount system
		System.out.println(priceDisplayService.getShowPriceDescription());

		System.out.print("How many hours does this show have? ");
		Integer duration = sc.nextInt();

		// create one list
		Event show = new Show(eventName, date, numberOfTickets, priceService, duration);

		// create the tickets and add do the list
		for (int j = 0; j < numberOfTickets; j++) {
			System.out.print("NORMAL or STUDENT: "); // Após ser mostrado os preços, ele pega a resposta
			TicketType ticketType = TicketType.valueOf(sc.next().toUpperCase());
			show.createAndAddTicket(ticketType);
		}
		programation.addEvent(show);

	}
}
