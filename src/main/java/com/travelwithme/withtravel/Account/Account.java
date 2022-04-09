package com.travelwithme.withtravel.Account;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity@Builder
@Getter@Setter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @GeneratedValue @Id
    private Long id;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String nickname;

    private String password;

    private boolean emailVerified;

    private String emailCheckToken;

    private LocalDateTime joinedAt;

    private String bio;

    private String url;

    private String occupation;

    @Embedded
    private Address address;

    private String profileImage;
}
