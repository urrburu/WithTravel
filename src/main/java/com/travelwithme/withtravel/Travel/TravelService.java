package com.travelwithme.withtravel.Travel;

import com.travelwithme.withtravel.Account.Account;
import com.travelwithme.withtravel.Plan.PlanRepository;
import com.travelwithme.withtravel.Spot.Spot;
import com.travelwithme.withtravel.Spot.SpotForm;
import com.travelwithme.withtravel.Spot.SpotRepository;
import com.travelwithme.withtravel.Tag.Tag;
import com.travelwithme.withtravel.Tag.TagRepository;
import com.travelwithme.withtravel.Travel.Form.TravelForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Service
@Transactional
@RequiredArgsConstructor
public class TravelService {

    private final TravelRepository travelRepository;
    private final SpotRepository spotRepository;
    private final TagRepository tagRepository;
    private final PlanRepository planRepository;


    public void newTravel(Account account, TravelForm travelForm) {
        Travel travel = Travel.builder().build();
        travel.setPath(travelForm.getPath());
        travel.setTravelName(travelForm.getTravelName());
        travel.setStartTime(travelForm.getStartTime());
        travel.setEndTime(travelForm.getEndTime());
        travel.setShortDescription(travelForm.getShortDescription());
        travel.setFullDescription(travelForm.getFullDescription());
        travel.setRecruiting(false);
        travel.setPublished(false);
        travel.setClosed(true);
        travelRepository.save(travel);
    }
}
