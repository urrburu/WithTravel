package com.travelwithme.withtravel.Travel;

import com.travelwithme.withtravel.Account.Account;
import com.travelwithme.withtravel.Account.UserAccount;
import com.travelwithme.withtravel.Plan.Plan;
import com.travelwithme.withtravel.Tag.Tag;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.*;



@Entity@Builder
@Getter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class Travel {

    @Id @GeneratedValue
    private Long id;

    @Column(unique = true)
    @NotNull
    private String travelName;

    @Column(unique = true)
    @Length(max = 30, min = 3)
    private String path;

    @Setter
    private String shortDescription;

    @Setter
    @Lob@Basic(fetch = FetchType.EAGER)
    private String fullDescription;

    @Lob@Basic(fetch = FetchType.EAGER)
    private String image;

    @ManyToMany
    private Set<Account> managers = new HashSet<>();

    @ManyToMany
    private Set<Account> members = new HashSet<>();

    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Plan> plans = new ArrayList<>();

    @ManyToMany
    private Set<Tag> tags = new HashSet<>();

    @Lob @Basic(fetch = FetchType.EAGER)
    private String travelImage;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private LocalDateTime publishedDateTime;

    private LocalDateTime recruitingUpdatedDate;

    private LocalDateTime closedDateTime;

    @Setter
    private boolean recruiting;

    @Setter
    private boolean published;

    @Setter
    private boolean closed;

    private boolean useBanner;

    private Integer totalCost;


    public Boolean isJoinable(UserAccount userAccount)  {
        Account account = userAccount.getAccount();
        return this.isPublished() && this.isRecruiting()
                && !this.members.contains(account) && !this.managers.contains(account);
    }

    public Boolean isMember(UserAccount userAccount) {
        return this.members.contains(userAccount.getAccount());
    }

    public Boolean isManager(UserAccount userAccount)  { return this.managers.contains(userAccount.getAccount());    }
    
    public void costCalculate(){this.totalCost = 0;for(Plan plan: plans){this.totalCost += plan.getCost();}    }

    public Boolean isPlanEmpty() {return this.getPlans().size()==0;}

}

