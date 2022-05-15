package com.travelwithme.withtravel.Spot;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class spotForm {

    private String spotName;

    private LocalDateTime startTime;

    private LocalDateTime endTime;
}
