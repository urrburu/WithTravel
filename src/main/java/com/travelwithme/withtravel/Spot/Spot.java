package com.travelwithme.withtravel.Spot;

import com.travelwithme.withtravel.Account.Account;
import lombok.*;
import org.springframework.data.geo.Point;
import javax.persistence.*;

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

    private String shortDescription;

    private Point point;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account firstMaker;

    public Hotel hotel( String spotName, String shortDescription, Point point, Account account){
        return new Hotel(spotName, shortDescription, point, account);
    }

    public Restaurant restaurant( String spotName, String shortDescription, Point point, Account account){
        return new Restaurant(spotName, shortDescription, point, account);
    }
}
