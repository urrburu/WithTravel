package com.travelwithme.withtravel.Travel;

import com.travelwithme.withtravel.Repository.TravelRepository;
import com.travelwithme.withtravel.Travel.Form.TravelContents;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TravelSettingService {

    private final TravelRepository travelRepository;

    public void modifyTravel(Travel travel, TravelContents travelContents) {
        travel.setFullDescription(travelContents.getFullDescription());
        travel.setShortDescription(travelContents.getShortDescription());

        travelRepository.save(travel);
    }
}
