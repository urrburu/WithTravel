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

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PlanService {

    private final TravelRepository travelRepository;
    private final SpotRepository spotRepository;
    private final PlanRepository planRepository;

    public Plan makeNewPlan(PlanForm planForm) {
        Spot spot = spotRepository.findBySpotName(planForm.getSpotName());
        Plan plan = Plan.builder()
                .cost(planForm.getCost())
                .startTime(planForm.getStartTime())
                .endTime(planForm.getEndTime())
                .longDescription(planForm.getLongDescription())
                .spot(spot)
                .build();
        return planRepository.save(plan);
    }

    public void addPlan(Account account, String travelPath, Plan plan) {
        Travel travel = travelRepository.findByPath(travelPath);
        if(travel.getManagers().contains(account)){
            travel.getPlans().add(plan);
        }
    }

    public void removePlan(Account account, String travelPath, Long planId) {
        Optional<Plan> plan = planRepository.findById(planId);
        Travel travel = travelRepository.findByPath(travelPath);
        if(travel.getManagers().contains(account) && plan.isPresent()){
            travel.getPlans().remove(plan);
        }
    }

    public List<Plan> findAllPlan(String travelPath) {
        Travel travel = travelRepository.findByPath(travelPath);
        return travel.getPlans();
    }
}
