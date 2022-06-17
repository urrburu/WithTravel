package com.travelwithme.withtravel.Spot;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class SpotForm {

    private String spotName;

    private String shortDescription;

    private String latitude;

    private String longitude;
}
