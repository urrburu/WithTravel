package com.travelwithme.withtravel.Travel;

import com.travelwithme.withtravel.Repository.TravelRepository;
import com.travelwithme.withtravel.Spot.Spot;
import com.travelwithme.withtravel.Spot.SpotForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TravelService {

    private final TravelRepository travelRepository;

    public List<Travel> findNineTravel(LocalDateTime now) {
        List<Travel> travels = travelRepository.findAll();
        List<Travel> travelList = new ArrayList<Travel>();
        for(int i=0;i<travels.size();++i){
            if(now.isAfter(travels.get(i).getStartTime()))travelList.add(travels.get(i));
            //Todo 이거 정렬하는 로직을 좀 생각해 봐야함
            if(travelList.size()==9)break;
        }
        return travelList;
    }

    public void addSpot(Travel travel, SpotForm spotForm) {
        travel = travelRepository.findByTravelName(travel.getTravelName());
        if(travel != null){
            List<Spot> spots = travel.getSpots();
            spots.add(new Spot(spotForm.getSpotName(), spotForm.getStartTime(), spotForm.getEndTime()));
            travel.setSpots(spots);
            travelRepository.save(travel);
        }
    }
}
