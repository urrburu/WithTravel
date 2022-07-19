package com.travelwithme.withtravel.Travel.Form;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class TravelForm {

    @NotNull(message = "여행 이름은 비워둘 수 없습니다.")
    @Length(max = 15)
    private String travelName;

    @NotNull(message = "여행을 찾는 경로는 비워둘 수 없습니다.")
    @Length(max = 15)
    private String path;

    @Length(max = 50)
    private String shortDescription;

    private String fullDescription;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime endTime;


}
