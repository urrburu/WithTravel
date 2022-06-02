package com.travelwithme.withtravel.Travel.Form;

import com.travelwithme.withtravel.Travel.Travel;
import lombok.Data;

@Data
public class TravelRecruiting {

    private boolean recruiting;//모집여부 설정

    private boolean published;//공개여부 설정

    private boolean closed;//폐쇄여부 설정

    public TravelRecruiting(Travel travel){
        this.recruiting = travel.isRecruiting();
        this.published = travel.isPublished();
        this.closed = travel.isClosed();
    }
}
