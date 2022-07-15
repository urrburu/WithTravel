package com.travelwithme.withtravel.Travel;

import com.travelwithme.withtravel.Account.Account;
import com.travelwithme.withtravel.Account.CurrentAccount;
import com.travelwithme.withtravel.Plan.Form.PlanForm;
import com.travelwithme.withtravel.Plan.Plan;
import com.travelwithme.withtravel.Spot.SpotForm;
import com.travelwithme.withtravel.Travel.Form.TravelForm;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/travel/")
@RequiredArgsConstructor
public class TravelController {

    private String travelLocation = "/travel/allTravel";
    private String travelPick = "travel/travelPick";
    private String travelMakeLocation = "/travel/makeTravel";
    //Todo add 콜과 remove 콜을 별도로 만들어줄 예정
    private String travelMembers = "/travel/TravelMember";

    private final TravelService travelService;
    private final TravelRepository travelRepository;
    private final ModelMapper modelMapper;

    @GetMapping("/allTravel")
    public String allTravel(Model model){
        List<Travel> travelList = travelRepository.findAll();
        model.addAttribute(travelList);
        return "/travel/allTravel";
    }

   @GetMapping("/newTravel")
   public String newTravel(Model model, @CurrentAccount Account account){
        model.addAttribute(account);
        model.addAttribute(new TravelForm());
       return travelMakeLocation;
   }

   @PostMapping("/newTravel")
    public String newTravelSubmit(@CurrentAccount Account account, @Valid TravelForm travelForm, Errors errors){

       if (errors.hasErrors()) {
           return travelMakeLocation;
       }
       Travel travel = travelService.newTravel(account, travelForm);
       return "redirect:/travel/"+travel.getPath();
   }
   @GetMapping("/{Path}")
    public String TravelOne(@CurrentAccount Account account, Model model, @PathVariable String Path){
        Travel travel = travelRepository.findByPath(Path);
        model.addAttribute(account);
        model.addAttribute(travel);
       return travelPick;
   }
    @GetMapping("/{Path}/members")
    public String TravelMembers(@CurrentAccount Account account, Model model, @PathVariable String Path){
        Travel travel = travelRepository.findByPath(Path);
        model.addAttribute(account);
        model.addAttribute(travel);
        return travelMembers;
    }

    @GetMapping("/{Path}/plans")
    public String AllPlanView(@CurrentAccount Account account, @PathVariable String Path, Model model){
        Travel travel = travelRepository.findByPath(Path);
        model.addAttribute(travel);
        model.addAttribute(account);
        return "/plan/planAll";
    }

}
