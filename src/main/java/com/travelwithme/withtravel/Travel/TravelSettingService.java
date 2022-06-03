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
        if(travel.isRecruiting()==false && travelRecruiting.isRecruiting()==true){ travel.setRecruitingUpdatedDate(LocalDateTime.now()); }
        if(travel.isPublished()==false && travelRecruiting.isPublished()==true){ travel.setPublishedDateTime(LocalDateTime.now()); }
        if(travel.isClosed()==false && travelRecruiting.isClosed()==true){ travel.setClosedDateTime(LocalDateTime.now()); }
        travel.setRecruiting(travelRecruiting.isRecruiting());
        travel.setPublished(travelRecruiting.isPublished());
        travel.setClosed(travelRecruiting.isClosed());
        if(travelRecruiting.isClosed()==true){
            travel.setRecruiting(false);
            travel.setPublished(false);
        }
        travelRepository.save(travel);
    }
}
