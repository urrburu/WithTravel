package com.travelwithme.withtravel.Travel.api;

import com.travelwithme.withtravel.Account.AccountService;
import com.travelwithme.withtravel.Travel.Travel;
import com.travelwithme.withtravel.Travel.TravelRepository;
import com.travelwithme.withtravel.Travel.TravelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TravelApiController {

    private final TravelRepository travelRepository;
    private final TravelService travelService;

    @GetMapping("/api/v3/simple-travel")
    public List<SimpleTravelDto> travelV3(){
        List<Travel> order = travelRepository.findAllWithManagers();
        List<SimpleTravelDto> result = new ArrayList<>();
        for (Travel t:order) {
            result.add(new SimpleTravelDto(t));
        }
        return result;
    }

}
