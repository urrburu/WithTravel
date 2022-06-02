package com.travelwithme.withtravel.Travel.Form;

import com.travelwithme.withtravel.Travel.Travel;
import lombok.Data;

@Data
public class TravelRecruiting {

    private boolean recruiting;//모집여부 설정

    //비공개로 멤버모집을 할 수 있나? -> 있어야지
    private boolean published;//공개여부 설정

    //공개되어 있는데 멤버모집을 하지 않을 수 있어야 함

    private boolean closed;//폐쇄여부 설정

    public TravelRecruiting(Travel travel){
        this.recruiting = travel.isRecruiting();
        this.published = travel.isPublished();
        this.closed = travel.isClosed();
    }
}
