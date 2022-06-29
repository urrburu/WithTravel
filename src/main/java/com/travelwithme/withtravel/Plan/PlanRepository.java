package com.travelwithme.withtravel.Plan;

import com.travelwithme.withtravel.Spot.Spot;
import com.travelwithme.withtravel.Travel.Travel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, Long> {

    Plan findByTravelAndSpot(Travel travel, Spot spot);
}
