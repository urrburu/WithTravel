package com.travelwithme.withtravel.Plan;

import com.travelwithme.withtravel.Spot.Spot;
import com.travelwithme.withtravel.Travel.Travel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface PlanRepository extends CrudRepository<Plan, Long> {

    Plan save(Plan plan);
    
    Optional<Plan> findById(Long memberId);

    List<Plan> findByTravelAndSpot(Travel travel, Spot spot);

    Plan findByTravelAndPlanName(Travel travel, String planName);
}
