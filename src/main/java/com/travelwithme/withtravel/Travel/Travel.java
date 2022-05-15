package com.travelwithme.withtravel.Travel;

import com.travelwithme.withtravel.Account.Account;
import com.travelwithme.withtravel.Spot.Spot;
import com.travelwithme.withtravel.Tag.Tag;
import lombok.*;

import javax.persistence.*;
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

    private String shortDescription;

    @Lob@Basic(fetch = FetchType.EAGER)
    private String fullDescription;

    @ManyToMany
    private Set<Account> managers;

    @ManyToMany
    private Set<Account> members;

    @ManyToMany
    private List<Spot> spots = new ArrayList<Spot>();

    @ManyToMany
    private Set<Tag> tags = new HashSet<>();

    @Column(unique = true)
    private String path;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    public void timeSetting(){
        startTime = spots.get(0).getStartTime();
        endTime = spots.get(spots.size()-1).getEndTime();
    }
}
