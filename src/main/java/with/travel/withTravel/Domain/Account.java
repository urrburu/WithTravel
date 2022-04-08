package with.travel.withTravel.Domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter @Setter
@EqualsAndHashCode(of = "id")
@Builder@AllArgsConstructor@NoArgsConstructor
public class Account {


    @Id @GeneratedValue
    private Long Id;

    private String Name;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String nickname;

    private String password;

    private Boolean emailVerified;

    private String emailCheckToken;

    private LocalDateTime joinedAt;

    private String bio;

    private Long age;

    @Embedded
    private Address address;

    private Boolean sex;

    @ManyToMany
    private Set<Place> VisitedPlaces;

    public void generateEmailCheckToken() {
        this.emailCheckToken = UUID.randomUUID().toString();

    }
}
