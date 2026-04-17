package model.interfaces;

import model.entities.enums.ExhibitionType;
import model.entities.enums.TheaterType;

public interface IPriceDisplayService {
    String getTheaterPriceDescription(TheaterType theaterType);
    String getExhibitionPriceDescription(ExhibitionType exhibitionType);
    String getShowPriceDescription();
}
