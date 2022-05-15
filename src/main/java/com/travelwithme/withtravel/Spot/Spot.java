package com.travelwithme.withtravel.Spot;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class Spot {

    @Id @GeneratedValue
    private Long id;

    private String spotName;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    public Spot(String spotName, LocalDateTime startTime, LocalDateTime endTime){
        this.spotName = spotName;this.startTime = startTime; this.endTime = endTime;

    }
}
