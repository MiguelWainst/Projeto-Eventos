package model.interfaces;

import model.entities.enums.ExhibitionType;
import model.entities.enums.TheaterType;
import model.entities.enums.TicketType;

public interface InterfacePriceService {
    public Double calculateTheaterPrice(TicketType ticketType, TheaterType theaterType, Integer numberOfTickets);
    public Double calculateExhibitionPrice(TicketType ticketType, Integer numberOfTickets, ExhibitionType exhibitionType);
    public Double calculateShowPrice(TicketType ticketType, Integer durationHours, Integer numberOfTickets);
}
