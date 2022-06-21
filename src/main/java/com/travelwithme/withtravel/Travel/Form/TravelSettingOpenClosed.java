package com.travelwithme.withtravel.Travel.Form;

import com.travelwithme.withtravel.Travel.Travel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TravelSettingOpenClosed {

    private boolean recruiting;

    private boolean published;

    private boolean closed;

    public TravelSettingOpenClosed(Travel travel){
        this.recruiting = travel.isRecruiting();
        this.published = travel.isPublished();
        this.closed = travel.isClosed();
    }

}
