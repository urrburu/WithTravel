package com.travelwithme.withtravel.Spot;

import com.travelwithme.withtravel.Account.Account;
import lombok.*;
import org.springframework.data.geo.Point;
import javax.persistence.*;

@Entity
@Getter@Setter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class Spot {

    @Id @GeneratedValue
    private Long id;

    private String spotName;

    private String shortDescription;

    private Point point;

    public SpotType spotType;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account firstMaker;


}
