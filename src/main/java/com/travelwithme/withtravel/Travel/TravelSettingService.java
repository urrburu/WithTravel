package com.travelwithme.withtravel.Travel;

import com.travelwithme.withtravel.Repository.TravelRepository;
import com.travelwithme.withtravel.Travel.Form.TravelContents;
import com.travelwithme.withtravel.Travel.Form.TravelRecruiting;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class TravelSettingService {

    private final TravelRepository travelRepository;

    public void modifyTravel(Travel travel, TravelContents travelContents) {
        travel.setFullDescription(travelContents.getFullDescription());
        travel.setShortDescription(travelContents.getShortDescription());
        if(travelContents.getStartTime()!= null)travel.setStartTime(travelContents.getStartTime());
        if(travelContents.getEndTime()!= null)travel.setEndTime(travelContents.getEndTime());
        travelRepository.save(travel);
    }

    public void modifyTravelPublish(Travel travel, TravelRecruiting travelRecruiting) {
        travel.setRecruiting(travelRecruiting.isRecruiting());

        travel.setPublished(travelRecruiting.isPublished());

        travel.setClosed(travelRecruiting.isClosed());

        travel.setRecruitingUpdatedDate(LocalDateTime.now());

    }
}
