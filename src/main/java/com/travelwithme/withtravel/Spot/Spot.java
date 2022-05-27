package com.travelwithme.withtravel.Spot;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Comparator;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class Spot implements Comparable<Spot>{

    @Id @GeneratedValue
    private Long id;

    private String spotName;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Integer cost;

    private String shortDescription;

    //Todo 위도, 경도를 입력하게 해서 kakao 지도를 이용해 지리를 보여줄 예정.

    public Spot(String spotName, LocalDateTime startTime, LocalDateTime endTime, String shortDescription, Integer cost){
        this.spotName = spotName;this.startTime = startTime; this.endTime = endTime;
        this.shortDescription = shortDescription; this.cost = cost;
    }


    @Override
    public String toString(){
        return spotName + " StartTime : "+startTime.toString()+" EndTime : "+endTime.toString();
    }

    @Override
    public int compareTo(Spot o) {
        if (this.startTime.isAfter(o.getStartTime()))return 1;
        else if(this.startTime.isBefore(o.getStartTime()))return -1;
        return 1;
    }
}
