package com.travelwithme.withtravel.Travel;

import com.travelwithme.withtravel.Account.Account;
import com.travelwithme.withtravel.Account.CurrentAccount;
import com.travelwithme.withtravel.Plan.Form.PlanForm;
import com.travelwithme.withtravel.Spot.SpotForm;
import com.travelwithme.withtravel.Travel.Form.TravelForm;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/travel/")
@RequiredArgsConstructor
public class TravelController {

    private String travelLocation = "/travel/allTravel";
    private final static String travelMakeUrl = "/travel/make";
    private String travelPick = "/travel/travelPick";
    private String travelMakeLocation = "/travel/makeTravel";
    //Todo add 콜과 remove 콜을 별도로 만들어줄 예정
    private String spotLocation = "/travel/modifySpot";

    private final TravelService travelService;
    private final TravelRepository travelRepository;
    private final ModelMapper modelMapper;


   @GetMapping("/newTravel")
   public String newTravel(Model model, @CurrentAccount Account account){
        model.addAttribute(account);
        model.addAttribute(new TravelForm());
       return travelLocation;
   }

   @PostMapping("/newTravel")
    public String newTravelSubmit( @CurrentAccount Account account, Model model, @Valid TravelForm travelForm){

       return "redirect:/travel/"+travelForm.getTravelName();
   }
   @GetMapping("/{travelName}")
    public String TravelOne(@CurrentAccount Account account, Model model, @PathVariable String travelName){

       return travelPick;
   }

}
