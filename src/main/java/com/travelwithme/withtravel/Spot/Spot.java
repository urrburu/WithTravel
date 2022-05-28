package com.travelwithme.withtravel.Spot;

import lombok.*;
import org.springframework.data.geo.Point;

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


    //Todo 장소를 저장하는 이 엔티티와 여행 한번 한번의 계획을 저장하는 Plan이라는 공간은 별도로 활용해야함 그렇기에 수정 필요.

    private Point point;


    public Spot(String spotName, LocalDateTime startTime, LocalDateTime endTime, String shortDescription, Integer cost,
    String latitude, String longitude){
        this.spotName = spotName;this.startTime = startTime; this.endTime = endTime;
        this.shortDescription = shortDescription; this.cost = cost;
        this.point = new Point(Double.parseDouble(latitude), Double.parseDouble(longitude));
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
