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

    public Spot(String spotName, LocalDateTime startTime, LocalDateTime endTime){
        this.spotName = spotName;this.startTime = startTime; this.endTime = endTime;

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
