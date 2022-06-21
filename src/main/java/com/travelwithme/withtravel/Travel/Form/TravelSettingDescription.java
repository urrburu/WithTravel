package com.travelwithme.withtravel.Travel.Form;

import com.travelwithme.withtravel.Travel.Travel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Basic;
import javax.persistence.FetchType;
import javax.persistence.Lob;

@Data
@NoArgsConstructor
public class TravelSettingDescription {


    private String shortDescription;

    @Lob
    @Basic(fetch = FetchType.EAGER)
    private String fullDescription;

    public TravelSettingDescription(Travel travel){
        this.shortDescription = travel.getShortDescription();
        this.fullDescription = travel.getFullDescription();
    }
}
