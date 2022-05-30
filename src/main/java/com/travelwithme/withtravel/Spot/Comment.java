package com.travelwithme.withtravel.Spot;

import javax.persistence.Lob;

public class Comment {
    //Todo 장소에 대한 댓글을 저장하는 모듈이 될 예정.

    private Integer point;//정수로 저장하고 꺼내쓸때는 소수점 붙여서 표현할 예정

    @Lob
    private String description;

}
