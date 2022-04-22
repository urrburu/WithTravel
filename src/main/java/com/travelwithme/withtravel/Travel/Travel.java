package com.travelwithme.withtravel.Travel;

import com.travelwithme.withtravel.Place.Place;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Entity
@Getter@Setter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class Travel {

    @Id @GeneratedValue
    private Long id;

    private String travelName;


}
