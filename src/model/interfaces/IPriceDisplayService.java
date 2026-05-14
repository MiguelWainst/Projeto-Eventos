package main.model.interfaces;

import main.model.entities.enums.ExhibitionType;
import main.model.entities.enums.TheaterType;

public interface IPriceDisplayService {
    String getTheaterPriceDescription(TheaterType theaterType);
    String getExhibitionPriceDescription(ExhibitionType exhibitionType);
    String getShowPriceDescription();
}
