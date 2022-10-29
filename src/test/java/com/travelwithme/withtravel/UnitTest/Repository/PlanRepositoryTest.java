package com.travelwithme.withtravel.UnitTest.Repository;

import com.travelwithme.withtravel.Plan.Plan;
import com.travelwithme.withtravel.Plan.PlanRepository;
import com.travelwithme.withtravel.Spot.SpotRepository;
import com.travelwithme.withtravel.Spot.SpotService;
import com.travelwithme.withtravel.Travel.TravelRepository;
import com.travelwithme.withtravel.Travel.TravelService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PlanRepositoryTest {

    @Autowired    PlanRepository planRepository;
    @Autowired    TravelRepository travelRepository;
    @Autowired    TravelService travelService;
    @Autowired    SpotService spotService;
    @Autowired    SpotRepository spotRepository;

    @Test
    void planFindTravelAndSpot(){
        //List<Plan> planList = planRepository.findByTravelAndSpot();
    }
}
