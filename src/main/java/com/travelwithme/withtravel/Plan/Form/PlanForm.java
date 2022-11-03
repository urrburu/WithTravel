package com.travelwithme.withtravel.Plan.Form;

import com.travelwithme.withtravel.Plan.PlanType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Lob;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PlanForm {

    private String planName;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime startTime;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime endTime;

    private Integer cost;

    @Lob
    private String longDescription;

    private PlanType planType = PlanType.SIGHTSEEING;

}
