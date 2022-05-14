package com.travelwithme.withtravel.Travel;

import com.travelwithme.withtravel.Place.Place;
import com.travelwithme.withtravel.Spot.Spot;
import com.travelwithme.withtravel.Tag.Tag;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Getter@Setter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class Travel {

    @Id @GeneratedValue
    private Long id;

    private String travelName;

    private String description;

    @ManyToMany
    private List<Spot> spots = new ArrayList<Spot>();

    @ManyToMany
    private Set<Tag> tags = new HashSet<>();

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    public void timeSetting(){
        startTime = spots.get(0).getStartTime();
        endTime = spots.get(spots.size()-1).getEndTime();
    }
}
