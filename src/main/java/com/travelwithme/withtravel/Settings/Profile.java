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

    @Length(max=50)
    private String url;

    @Length(max = 50)
    private String occupation;

    @Length(max = 10)
    private String city;

    @Length(max = 10)
    private String localNameOfCity;

    @Length(max = 10)
    private String province;

    private String profileImage;

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
