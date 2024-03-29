package com.travelwithme.withtravel.Plan;

import com.travelwithme.withtravel.Account.Account;
import com.travelwithme.withtravel.Spot.Spot;
import com.travelwithme.withtravel.Travel.Travel;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
/*기본 개념은 계획은 복사해가면서 시간만 수정할 수 있는 클래스로 만들고 싶음*/

@Entity@Getter@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Plan implements Comparable<Plan>{

    @Id @GeneratedValue
    private Long id;

    private String planName;

    @ManyToOne
    private Travel travel;

    @ManyToOne
    private Account createdBy;

    @ManyToOne
    private Spot travelSpot;
    //Todo Plan 코드가 앞으로 spot을 대체해야함, spot-> 장소를 저장할 수는 있어야 하지만 Plan은 Travel에 종속된 저장형식 이어야함.
    //그런 Plan에서 Spot을 끌어와서 저장하는 걸로

    @Lob
    private String longDescription;

    @Setter
    private LocalDateTime startTime;

    @Setter
    private LocalDateTime endTime;

    @Setter
    private Integer cost;

    @Enumerated(EnumType.STRING)
    private PlanType planType;

    @Override
    public int compareTo(Plan o) {
        if(this.startTime.equals(o.startTime)){
            return this.endTime.compareTo(o.endTime);
        }
        return this.startTime.compareTo(o.startTime);
    }
}
