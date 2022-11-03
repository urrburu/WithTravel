package com.travelwithme.withtravel.Plan;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.travelwithme.withtravel.Account.Account;
import com.travelwithme.withtravel.Account.CurrentAccount;
import com.travelwithme.withtravel.Plan.Form.PlanForm;
import com.travelwithme.withtravel.Plan.Form.PlanModify;
import com.travelwithme.withtravel.Spot.SpotService;
import com.travelwithme.withtravel.Travel.Travel;
import com.travelwithme.withtravel.Travel.TravelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
@RequestMapping("travel/{travelPath}/plan")
public class PlanController {

    private final PlanRepository planRepository;
    private final PlanService planService;
    private final SpotService spotService;
    private final ObjectMapper objectMapper;
    private final TravelRepository travelRepository;

    private static final String NEW_PLAN_URL = "/newPlan";

    @GetMapping(NEW_PLAN_URL)
    public String makePlanView(@CurrentAccount Account account, @PathVariable String travelPath, Model model) throws JsonProcessingException {
        Travel travel = travelRepository.findByPath(travelPath);
        model.addAttribute("account", account);
        model.addAttribute("travel",travel);
        model.addAttribute(new PlanForm());
        return "plan/newPlan";
    }
    @PostMapping(NEW_PLAN_URL)
    public String addonPlan(@CurrentAccount Account account, @PathVariable String travelPath, @Valid PlanForm planForm){
        Plan plan = planService.makeNewPlan(account, travelPath, planForm);
        planService.addPlan(account, travelPath, plan);
        return "redirect:/travel/"+travelPath+"/plans";
    }
    /*
    @PostMapping(NEW_PLAN_URL+"/remove")
    public String removePlan(@CurrentAccount Account account, @RequestBody PlanForm planForm, @PathVariable String travelPath){
        planService.removePlan(account, travelPath, planForm);
        return "redirect:/travel/"+travelPath+"/plans";
    }*/

    @GetMapping("{planName}/modifyPlan")
    public String modifyPlanView(@CurrentAccount Account account, @PathVariable String travelPath,@PathVariable String planName, Model model){
        Plan plan = planService.findPlan(account, travelPath, planName);
        model.addAttribute(account);
        model.addAttribute(new PlanModify(plan));

        return "plan/modifyPlan";
    }


}
