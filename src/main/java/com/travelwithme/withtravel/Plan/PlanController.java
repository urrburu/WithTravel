package com.travelwithme.withtravel.Plan;

import com.travelwithme.withtravel.Account.Account;
import com.travelwithme.withtravel.Account.CurrentAccount;
import com.travelwithme.withtravel.Plan.Form.PlanForm;
import com.travelwithme.withtravel.Travel.Travel;
import lombok.AllArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("travel/{travelPath}/plan")
public class PlanController {

    private final PlanRepository planRepository;
    private final PlanService planService;


    @GetMapping("/newPlan")
    public String makePlan(@CurrentAccount Account account, @PathVariable String travelPath, Model model)
    {
        model.addAttribute(account);
        model.addAttribute(new PlanForm());
        return "plan/newPlan";
    }
    @PostMapping("/newPlan")
    public String addonPlan(@CurrentAccount Account account, @PathVariable String travelPath, @Valid PlanForm planForm){
        Plan plan = planService.makeNewPlan(planForm);
        planService.addPlan(account, travelPath, plan);
        return "redirect:/travel"+travelPath+"/plan";
    }


}
