package model.services;

import model.entities.enums.ExhibitionType;
import model.entities.enums.TheaterType;
import model.entities.enums.TicketType;
import model.interfaces.IPriceService;

public class PriceCalculateService implements IPriceService {
    /*
    Classe responsável por fazer o cálculo do
    preço dos tickets e aplicar as regras de negócio.
    */

    // Preços dos tipos de teatros "theaterType":
    private static final int NORMAL_CLASSIC_THEATER_PRICE = 80;    // classic theater price without discount.
    private static final int DISCOUNT_CLASSIC_THEATER_PRICE = 70;    // classic theater with discount.
    private static final int NORMAL_MODERN_THEATER_PRICE = 70;    // modern theater without discount.
    private static final int DISCOUNT_MODERN_THEATER_PRICE = 60;    // modern theater with discount.
    private static final int STUDENT_PRICE = 55;    // student pay same price for every theater ticket

    // Preços dos tipos de exibições "exhibitionType":
    private static final int NORMAL_CHINESE = 50;
    private static final int NORMAL_CHINESE_DISCOUNT = 40; //"D" means "DISCOUNT" (price with the discount)
    private static final int NORMAL_MODERN = 30;
    private static final int NORMAL_MODERN_DISCOUNT = 20;
    private static final int STUDENT = 0;

    @Override
    public Double calculateTheaterPrice(TicketType ticketType, TheaterType theaterType, Integer numberOfTickets) {
        /*
         * If a normal person is alone on a theater he will
         * pay the full price.
         *
         * If a normal person is with more people then they will
         * get a discount.
         *
         * Students pay the same price for classic and modern
         * theater, dont matter if he's alone or not.
         * But if a normal person is accompanied by a student
         * he will get the discount.
         */
        double totalPrice = 0;

        if (ticketType == TicketType.NORMAL) {
            totalPrice = theaterType == TheaterType.CLASSIC ? (numberOfTickets > 1 ? DISCOUNT_CLASSIC_THEATER_PRICE : NORMAL_CLASSIC_THEATER_PRICE)
                    : (numberOfTickets > 1 ? DISCOUNT_MODERN_THEATER_PRICE : NORMAL_MODERN_THEATER_PRICE);
        } else if (ticketType == TicketType.STUDENT) {
            totalPrice = STUDENT_PRICE;
        }

        return totalPrice;
    }

    @Override
    public Double calculateExhibitionPrice(TicketType ticketType, Integer numberOfTickets, ExhibitionType exhibitionType) {
        /*
         * The price depends on how many tickets will be bought.
         * And what type of exhibition is:
         *
         * 1 ticket of Chinese art = $50
         * +1 ticket = $40 each
         *
         * 1 ticket of modern art = $30
         * +1 ticket = 20$
         *
         * But if its a student type it will be free access for
         * both type of exhibition.
         */
        double price = 0.0;
        if (exhibitionType == ExhibitionType.CHINESE_ART) {
            price = ticketType == TicketType.NORMAL ? (numberOfTickets > 1 ? NORMAL_CHINESE_DISCOUNT : NORMAL_CHINESE)
                    : STUDENT;
        } else if (exhibitionType == ExhibitionType.MODERN_ART) {
            price = ticketType == TicketType.NORMAL ? (numberOfTickets > 1 ? NORMAL_MODERN_DISCOUNT : NORMAL_MODERN)
                    : STUDENT;
        }
        return price;
    }

    @Override
    public Double calculateShowPrice(TicketType ticketType, Integer durationHours, Integer numberOfTickets) {
        /*
         * The price depends on how many tickets will be bought.
         * 1 tickets = $150 per hour
         * +1 ticket = $120 per hour each
         *
         * But if its a student type it will cost only 120$
         * independent if de show is longer than 1 hour.
         */
        double price = 0;
        if (ticketType == TicketType.NORMAL) { // Rule if ticket is normal type.
            price = numberOfTickets > 1 ? 120 * durationHours : 150 * durationHours;
        } else if (ticketType == TicketType.STUDENT) price = 100; // Rule if its student type.
        return price;
    }
}