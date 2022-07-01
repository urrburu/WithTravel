package com.travelwithme.withtravel.Plan;

import com.travelwithme.withtravel.Spot.Spot;
import com.travelwithme.withtravel.Travel.Travel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface PlanRepository extends JpaRepository<Plan, Long> {

    Plan findByTravelAndSpot(Travel travel, Spot spot);
}
