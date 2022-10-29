package com.travelwithme.withtravel.Spot;

import com.travelwithme.withtravel.Account.Account;
import org.springframework.data.geo.Point;

public class Hotel extends Spot{


    public Hotel(String spotName, Point point, Account account, SpotType spotType) {
        this.setSpotName(spotName);
        this.setPoint(point);
        this.setFirstMaker(account);
        this.setSpotType(spotType);
    }
}
