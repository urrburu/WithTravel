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

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    public TravelContents(Travel travel){
        this.startTime = travel.getStartTime();
        this.endTime = travel.getEndTime();
        this.shortDescription = travel.getShortDescription();
        this.fullDescription = travel.getFullDescription();

    }
}
