package main.model.interfaces;

import main.model.entities.enums.ExhibitionType;
import main.model.entities.enums.TheaterType;
import main.model.entities.enums.TicketType;

public interface IPriceService {
    Double calculateTheaterPrice(TicketType ticketType, TheaterType theaterType, Integer numberOfTickets);
    Double calculateExhibitionPrice(TicketType ticketType, Integer numberOfTickets, ExhibitionType exhibitionType);
    Double calculateShowPrice(TicketType ticketType, Integer durationHours, Integer numberOfTickets);
}
