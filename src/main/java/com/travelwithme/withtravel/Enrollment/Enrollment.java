package com.travelwithme.withtravel.Enrollment;

import com.travelwithme.withtravel.Account.Account;
import com.travelwithme.withtravel.Travel.Travel;

import javax.persistence.*;

@Entity
public class Enrollment {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "TRAVEL_ID")
    private Travel travel;

    @ManyToOne
    @JoinColumn(name = "Account_id")
    private Account JoinWant;
}
