package com.travelwithme.withtravel.Settings;

import lombok.Data;
import lombok.Getter;

@Data
public class Notification {

    private boolean travelCreatedByWeb;

    private boolean travelEnrollmentResultByWeb;

    private boolean travelUpdatedByWeb;
}
