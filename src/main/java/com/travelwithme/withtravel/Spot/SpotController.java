package com.travelwithme.withtravel.Spot;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class SpotController {

    private final SpotRepository spotRepository;

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
}
