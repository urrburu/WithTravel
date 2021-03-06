package com.travelwithme.withtravel.Spot;

import com.travelwithme.withtravel.Account.Account;
import com.travelwithme.withtravel.Account.CurrentAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class SpotController {

    private final SpotRepository spotRepository;
    private final SpotService spotService;

    @GetMapping("/spots/{spotName}")
    public String spotView(@PathVariable String spotName, Model model){
        Spot spot = spotRepository.findBySpotName(spotName);
        if(spot==null){

        }
        model.addAttribute(spot);
        return "Spot/spotView";
    }
    @GetMapping("/spot/all")
    public String spotView(Model model){
        List<Spot> spots = spotRepository.findAll();
        model.addAttribute(spots);
        return "Spot/allView";
    }

    @GetMapping("/spots/newspot")
    public String newSpot(@CurrentAccount Account account, Model model){
        model.addAttribute(account);
        model.addAttribute(new SpotForm());
        return "/Spot/newSpot";
    }
    @PostMapping("/spots/newspot")
    public String newSpotSubmit(@CurrentAccount Account account,  @Valid SpotForm spotForm){
        Spot spot = spotService.newSpot(account, spotForm);
        return "redirect:/spots/newspot";
    }
}
