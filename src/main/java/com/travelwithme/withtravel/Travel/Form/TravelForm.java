package com.travelwithme.withtravel.Travel.Form;

import com.travelwithme.withtravel.Account.Account;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class TravelForm {

    private String travelName;

    private String shortDescription;

    private String fullDescription;

    private Set<Account> managers = new HashSet<>();

}
