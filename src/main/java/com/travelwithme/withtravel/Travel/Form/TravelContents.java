package com.travelwithme.withtravel.Travel.Form;

import com.travelwithme.withtravel.Travel.Travel;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;


@Data
public class TravelContents {

    @Length(max = 50)
    private String shortDescription;

    private String fullDescription;

    public TravelContents(Travel travel){
        this.shortDescription = travel.getShortDescription();
        this.fullDescription = travel.getFullDescription();

    }
/*
    public TravelContents(String shortDescription, String fullDescription){
        //It's for test builder
        this.shortDescription = shortDescription;
        this.fullDescription = fullDescription;
        this.startTime = LocalDateTime.now().plusMonths(1);
        this.endTime = LocalDateTime.now().plusMonths(2);
    }

 */
}
