package com.travelwithme.withtravel.Plan.Form;

import com.travelwithme.withtravel.Plan.Plan;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PlanModify {

    private String planName;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime startTime;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime endTime;

    private Integer cost;

    private String longDescription;

    public PlanModify(Plan plan){
        this.planName = plan.getPlanName();
        this.cost = plan.getCost();
        this.startTime = plan.getStartTime();
        this.endTime = plan.getEndTime();
        this.longDescription = plan.getLongDescription();
    }
}
