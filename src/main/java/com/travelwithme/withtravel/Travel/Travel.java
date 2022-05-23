package com.travelwithme.withtravel.Travel;

import com.travelwithme.withtravel.Account.Account;
import com.travelwithme.withtravel.Account.UserAccount;
import com.travelwithme.withtravel.Spot.Spot;
import com.travelwithme.withtravel.Tag.Tag;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Getter@Setter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Travel {

    @Id @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String travelName;

    private String shortDescription;

    @Lob@Basic(fetch = FetchType.EAGER)
    private String fullDescription;

    @Lob@Basic(fetch = FetchType.EAGER)
    private String image;

    @ManyToMany
    private Set<Account> managers;

    @ManyToMany
    private Set<Account> members;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Spot> spots = new ArrayList<>();

    @ManyToMany
    private Set<Tag> tags = new HashSet<>();

//    @Lob @Basic(fetch = FetchType.EAGER)
//    private String Image;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private LocalDateTime publishedDateTime;

    private LocalDateTime recruitingUpdatedDate;

    private LocalDateTime closedDateTime;

    private boolean recruiting;

    private boolean published;

    private boolean closed;

    private boolean useBanner;



    public void timeSetting(){
        startTime = spots.get(0).getStartTime();
        endTime = spots.get(spots.size()-1).getEndTime();
    }

    public Boolean isJoinable(UserAccount userAccount)  {
        Account account = userAccount.getAccount();
        return this.isPublished() && this.isRecruiting()
                && !this.members.contains(account) && !this.managers.contains(account);
    }

    public Boolean isMember(UserAccount userAccount) {
        return this.members.contains(userAccount.getAccount());
    }

    public Boolean isManager(UserAccount userAccount)  { return this.managers.contains(userAccount.getAccount());    }
}

