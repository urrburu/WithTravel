package com.travelwithme.withtravel.Plan;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class PlanController {

    private final PlanRepository planRepository;

}
