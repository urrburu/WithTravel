package com.travelwithme.withtravel.Account;


import com.travelwithme.withtravel.Tag.Tag;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

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

    private String loginCheckToken;

    private LocalDateTime joinedAt;

    private String bio;

    private String url;

    private String occupation;

    private boolean travelCreatedByWeb;

    private boolean travelEnrollmentResultByWeb;

    private boolean travelUpdatedByWeb;

    @Embedded
    private Address address;
    //엔티티의 값일 뿐.

    @Lob@Basic(fetch = FetchType.EAGER)
    private String profileImage;

    @ManyToMany
    private Set<Tag> tags = new HashSet<>();


    private LocalDateTime lastSendDateTime;

    public void generateEmailCheckToken(){
        this.emailCheckToken = UUID.randomUUID().toString();
    }
    public boolean CheckValidToken(String Token){return Token.equals(this.emailCheckToken);}
    public void completeSignUp() {
        this.setEmailVerified(true);
        this.setJoinedAt(LocalDateTime.now());
    }
    public boolean canSendConfirmEmail(){
        if(lastSendDateTime==null){
            lastSendDateTime = LocalDateTime.now();
            return true;
        }
        if(LocalDateTime.now().isAfter(lastSendDateTime.plusHours(1))){
            lastSendDateTime = LocalDateTime.now();
            return true;
        }
        return false;
    }

    public boolean isValidToken(String token) {
        if(token.equals(loginCheckToken))return true;
        return false;
    }

    public void generateLoginEmailToken() {
        this.loginCheckToken = UUID.randomUUID().toString();
    }
}
/*
* 엔티티를 변경한다고 해서 api까지 변경되어서는 안된다
* 엔티티를 그대로 외부에 노출하는 것은 안 좋은 설계이고
*
* 따라서 별도의 DTO를 만들어 반환하는 작업이 유리하다.
* */