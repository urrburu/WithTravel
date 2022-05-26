package com.travelwithme.withtravel.Travel.Form;

import com.travelwithme.withtravel.Travel.Travel;
import lombok.Data;
import org.hibernate.validator.constraints.Length;


@Data
public class TravelContents {


    private Long id;

    @Length(max = 255)
    private String shortDescription;

    private String fullDescription;

    public TravelContents(Travel travel){

        this.shortDescription = travel.getShortDescription();
        this.fullDescription = travel.getFullDescription();

    }
}
