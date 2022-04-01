package with.travel.withTravel.Domain;

import javax.persistence.*;

@Entity
public class Place {


    @Id@GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private PlaceType placeType;

    private String LocationName;

    private Double latitude;

    private Double longitude;
}
