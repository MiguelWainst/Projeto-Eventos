package model.services;

import model.entities.enums.ExhibitionType;
import model.entities.enums.TheaterType;
import model.interfaces.IPriceDisplayService;

public class PriceDisplayService implements IPriceDisplayService {

    /*
    Classe responsável por mostrar na tela do usuário
    os preços dos tickets que eles requisitaram no console.
     */

    @Override
    public String getTheaterPriceDescription(TheaterType theaterType) {
        /*
        Condição: Os preços exibidos na tela serão os condizentes
        com o que o usuário escolheu. Essa informação deriva do
        "theaterType" digitado na classe "EventService"
         */

        if (theaterType == TheaterType.CLASSIC) {
            return "PRICES: \n"
                    + "Normal ticket: $80 (1 ticket) or $70 each (2+ tickets)\n"
                    + "Student price ticket: $55 each\n";
        } else if (theaterType == TheaterType.MODERN) {
            return "PRICES: \n"
                    + "Normal ticket: $70 (1 ticket) or $60 each (2+ tickets)\n"
                    + "Student price ticket: $55 each\n";
        }
        return "null";
    }

    @Override
    public String getExhibitionPriceDescription(ExhibitionType exhibitionType) {
        /*
        Condição: Os preços exibidos na tela serão os condizentes
        com o que o usuário escolheu. Essa informação deriva do
        "exhibitionType" digitado na classe "EventService"
         */

        if (exhibitionType == ExhibitionType.CHINESE_ART) {
            return "PRICES: \n"
                    + "Normal ticket: $50 (1 ticket) or $40 each (2+ tickets)\n"
                    + "Student price ticket: FREE";
        } else if (exhibitionType == ExhibitionType.MODERN_ART) {
            return "PRICES: \n"
                    + "Normal ticket: $30 (1 ticket) or $20 each (2+ tickets)\n"
                    + "Student price ticket: FREE\n";
        }
        return "null";
    }

    @Override
    public String getShowPriceDescription() {
        return "PRICES: \n"
                + "Normal ticket: $150 (per hour) (1 ticket) or $120 (per hour) each (2+ tickets) \n"
                + "Student price ticket: $100 only (once) each";
    }
}
