package com.travelwithme.withtravel.Travel.Form;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class TravelForm {

    @NotNull(message = "여행 이름은 비워둘 수 없습니다.")
    @Length(max = 15)
    private String travelName;

    @Length(max = 255)
    private String shortDescription;

    private String fullDescription;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

}
