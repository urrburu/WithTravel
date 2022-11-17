package com.travelwithme.withtravel.Plan;

import com.travelwithme.withtravel.Account.Account;
import com.travelwithme.withtravel.Plan.Form.PlanForm;
import com.travelwithme.withtravel.Spot.Spot;
import com.travelwithme.withtravel.Spot.SpotRepository;
import com.travelwithme.withtravel.Travel.Travel;
import com.travelwithme.withtravel.Travel.TravelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PlanService {

    private final TravelRepository travelRepository;
    private final SpotRepository spotRepository;
    private final PlanRepository planRepository;

    public Plan makeNewPlan(Account account, String travelPath, PlanForm planForm, String spotName) {
        Travel travel = travelRepository.findByPath(travelPath);
        Spot spot = spotRepository.findBySpotName(spotName);
        Plan plan = Plan.builder()
                .planName(planForm.getPlanName())
                .travelSpot(spot)
                .travel(travel)
                .cost(planForm.getCost())
                .longDescription(planForm.getLongDescription())
                .startTime(planForm.getStartTime())
                .endTime(planForm.getEndTime())
                .createdBy(account)
                .build();
        return plan;
    }

    public Plan makeNewPlan(Account account, String travelPath, PlanForm planForm) {

        Travel travel = travelRepository.findByPath(travelPath);
        Plan plan = Plan.builder()
                .planName(planForm.getPlanName())
                .travel(travel)
                .cost(planForm.getCost())
                .longDescription(planForm.getLongDescription())
                .startTime(planForm.getStartTime())
                .endTime(planForm.getEndTime())
                .createdBy(account)
                .build();
        return plan;
    }
    public void addPlan(Account account, String travelPath, Plan plan) {
        Travel travel = travelRepository.findByPath(travelPath);
        if(travel.getManagers().contains(account)){
            travel.getPlans().add(plan);
        }
    }

    /*
    public void removePlan(Account account, String travelPath, PlanForm planForm) {
        Spot spot = spotRepository.findBySpotName(planForm.getSpotName());
        Travel travel = travelRepository.findByPath(travelPath);
        Plan plan = planRepository.findByTravelAndSpot(travel, spot);
        if(travel.getManagers().contains(account) && plan != null){
            travel.getPlans().remove(plan);
        }
    }
    */
    public List<Plan> findAllPlan(String travelPath) {
        Travel travel = travelRepository.findByPath(travelPath);
        return travel.getPlans();
    }


    public Plan findPlan(String travelPath, String planName) {
        Travel travel = travelRepository.findByPath(travelPath);
        return planRepository.findByTravelAndPlanName(travel, planName);
    }
}
