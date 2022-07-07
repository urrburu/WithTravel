package com.travelwithme.withtravel.Spot;

import com.travelwithme.withtravel.Account.Account;
import org.springframework.data.geo.Point;

public class Restaurant extends Spot{

    public Restaurant(String spotName, String shortDescription, Point point, Account account){
        this.setSpotName(spotName);
        this.setShortDescription(shortDescription);
        this.setPoint(point);
        this.setFirstMaker(account);
    }
}
