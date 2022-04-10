package com.travelwithme.withtravel.Account;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {

    private String city;


    private String localNameOfCity;


    private String province;

    @Override
    public String toString() {
        return String.format("%s(%s)/%s", city, localNameOfCity, province);
    }

    protected Address(){

    }

    public Address(String city, String localNameOfCity, String province) {
        this.city = city;
        this.localNameOfCity = localNameOfCity;
        this.province = province;
    }
}
