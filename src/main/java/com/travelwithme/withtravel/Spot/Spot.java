package com.travelwithme.withtravel.Spot;

import com.travelwithme.withtravel.Account.Account;
import lombok.*;
import org.springframework.data.geo.Point;
import javax.persistence.*;

@Entity
@Getter@Builder
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class Spot {

    @Id @GeneratedValue
    private Long id;

    private String spotName;

    @Setter
    private String shortDescription;

    private Point point;

    @Setter
    @Enumerated(EnumType.STRING)
    public SpotType spotType;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account firstMaker;


}
