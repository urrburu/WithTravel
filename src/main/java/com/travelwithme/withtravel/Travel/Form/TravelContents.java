package com.travelwithme.withtravel.Travel.Form;

import com.travelwithme.withtravel.Travel.Travel;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;


@Data
public class TravelContents {


    private Long id;

    @Length(max = 50)
    private String shortDescription;

    private String fullDescription;


    public TravelContents(Travel travel){
        this.shortDescription = travel.getShortDescription();
        this.fullDescription = travel.getFullDescription();
    }
}
