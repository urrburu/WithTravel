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

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PlanService {

    private final TravelRepository travelRepository;
    private final SpotRepository spotRepository;
    private final PlanRepository planRepository;

    public Plan makeNewPlan(Account account,String travelPath, PlanForm planForm) {
        Spot spot = spotRepository.findBySpotName(planForm.getSpotName());
        Travel travel = travelRepository.findByPath(travelPath);
        Plan plan = new Plan();
        plan.setCost(planForm.getCost());
        plan.setCreatedBy(account);
        plan.setLongDescription(planForm.getLongDescription());
        plan.setStartTime(planForm.getStartTime());
        plan.setEndTime(planForm.getEndTime());
        plan.setSpot(spot);
        plan.setTravel(travel);
        return planRepository.save(plan);
    }

    public void addPlan(Account account, String travelPath, Plan plan) {
        Travel travel = travelRepository.findByPath(travelPath);
        if(travel.getManagers().contains(account)){
            travel.getPlans().add(plan);
        }
    }

    public void removePlan(Account account, String travelPath, PlanForm planForm) {
        Spot spot = spotRepository.findBySpotName(planForm.getSpotName());
        Travel travel = travelRepository.findByPath(travelPath);
        Plan plan = planRepository.findByTravelAndSpot(travel, spot);
        if(travel.getManagers().contains(account) && plan != null){
            travel.getPlans().remove(plan);
        }
    }

    public List<Plan> findAllPlan(String travelPath) {
        Travel travel = travelRepository.findByPath(travelPath);
        return travel.getPlans();
    }

    public List<Spot> getPlans(String travelPath) {
        Travel travel = travelRepository.findByPath(travelPath);
        List<Spot> spots = new ArrayList<>();
        for(Plan plan: travel.getPlans()){spots.add(plan.getSpot()); }
        return spots;
    }
}
