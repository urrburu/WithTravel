package with.travel.withTravel.Domain;


import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {
    private String District;
    private String City;
    private String StreetName;
    private String zipcode;

    protected Address() {
    }
    public Address(String District, String city, String street, String zipcode) {
        this.District = District;
        this.City = city;
        this.StreetName = street;
        this.zipcode = zipcode;
    }
}
