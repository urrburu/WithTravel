package com.travelwithme.withtravel.Travel;

import com.travelwithme.withtravel.Account.Account;
import com.travelwithme.withtravel.Plan.PlanRepository;
import com.travelwithme.withtravel.Plan.PlanService;
import com.travelwithme.withtravel.Spot.Spot;
import com.travelwithme.withtravel.Spot.SpotForm;
import com.travelwithme.withtravel.Spot.SpotRepository;
import com.travelwithme.withtravel.Spot.SpotService;
import com.travelwithme.withtravel.Tag.Tag;
import com.travelwithme.withtravel.Tag.TagRepository;
import com.travelwithme.withtravel.Travel.Form.TravelForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Service
@Transactional
@RequiredArgsConstructor
public class TravelService {

    private final TravelRepository travelRepository;
    private final SpotRepository spotRepository;
    private final SpotService spotService;
    private final TagRepository tagRepository;
    private final PlanRepository planRepository;
    private final PlanService planService;


    @Transactional
    public Travel newTravel(Account account,@Valid TravelForm travelForm) {
        Travel travel = Travel.builder()
                .path(travelForm.getPath())
                .travelName(travelForm.getTravelName())
                .shortDescription(travelForm.getShortDescription())
                .fullDescription(travelForm.getFullDescription())
                .recruiting(false)
                .published(false)
                .closed(false)
                .build();
        travel.getManagers().add(account);
        travel.setStartTime(travelForm.getStartTime());
        travel.setEndTime(travelForm.getEndTime());
        return travelRepository.save(travel);
    }
}
