package model.interfaces;

import model.entities.enums.ExhibitionType;
import model.entities.enums.TheaterType;
import model.entities.enums.TicketType;

public interface IPriceService {
    Double calculateTheaterPrice(TicketType ticketType, TheaterType theaterType, Integer numberOfTickets);
    Double calculateExhibitionPrice(TicketType ticketType, Integer numberOfTickets, ExhibitionType exhibitionType);
    Double calculateShowPrice(TicketType ticketType, Integer durationHours, Integer numberOfTickets);
}
