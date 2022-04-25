package com.travelwithme.withtravel.Settings;

import com.travelwithme.withtravel.Account.Account;
import com.travelwithme.withtravel.Account.Address;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
public class Profile {

    @Length(max = 35)
    private String bio;

    private String url;

    private String occupation;

    private String city;

    private String localNameOfCity;

    private String province;

    public Profile(Account account){
        this.bio = account.getBio();
        this.url = account.getUrl();
        this.occupation = account.getOccupation();
        Address address = account.getAddress();
        if(address!=null){
            this.city = address.getCity();
            this.localNameOfCity = address.getLocalNameOfCity();
            this.province = address.getProvince();
        }

    }
}
