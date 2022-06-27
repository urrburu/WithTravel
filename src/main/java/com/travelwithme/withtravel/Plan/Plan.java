package com.travelwithme.withtravel.Plan;

import com.travelwithme.withtravel.Spot.Spot;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity@Getter@Setter@Builder
@EqualsAndHashCode(of="id")
public class Plan{

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private Spot spot;
    //Todo Plan 코드가 앞으로 spot을 대체해야함, spot-> 장소를 저장할 수는 있어야 하지만 Plan은 Travel에 종속된 저장형식 이어야함.
    //그런 Plan에서 Spot을 끌어와서 저장하는 걸로

    private String longDescription;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Integer cost;


}
