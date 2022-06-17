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


}
