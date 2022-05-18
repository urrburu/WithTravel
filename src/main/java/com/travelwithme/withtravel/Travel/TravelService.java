package com.travelwithme.withtravel.Travel;

import com.travelwithme.withtravel.Account.Account;
import com.travelwithme.withtravel.Repository.TravelRepository;
import com.travelwithme.withtravel.Spot.Spot;
import com.travelwithme.withtravel.Spot.SpotForm;
import com.travelwithme.withtravel.Spot.SpotRepository;
import com.travelwithme.withtravel.Travel.Form.TravelForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
@Transactional
@RequiredArgsConstructor
public class TravelService {

    private final TravelRepository travelRepository;
    private final SpotRepository spotRepository;

    public List<Travel> findNineTravel(LocalDateTime now) {
        List<Travel> travels = travelRepository.findAll();
        List<Travel> travelList = new ArrayList<Travel>();
        for(int i=0;i<travels.size();++i){
            if(now.isAfter(travels.get(i).getStartTime()))travelList.add(travels.get(i));
            //Todo 이거 정렬하는 로직을 좀 생각해 봐야함 현재는 travelList에서 아직 출발하지 않은
            // TOdo 출발하지 않은 여행 9개를 리스트 순서에 따라서 갖고 오는건데
            if(travelList.size()==9)break;
        }
        return travelList;
    }

    public void newTravelMake(TravelForm travelForm, Account account){
        Travel travel = new Travel().builder().build();
        Set<Account> accounts = new HashSet<>();
        accounts.add(account);
        travel.setManagers(accounts);
        travel.setTravelName(travelForm.getTravelName());
        travel.setShortDescription(travelForm.getShortDescription());
        travel.setFullDescription(travelForm.getFullDescription());
        travel.setPublishedDateTime(LocalDateTime.now());
        travel.setClosedDateTime(LocalDateTime.now().plusDays(7));

        travelRepository.save(travel);
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

    public void removeSpot(Travel travel, String spotName){
        travel = travelRepository.findByTravelName(travel.getTravelName());
        if(travel != null){
            List<Spot> spots = travel.getSpots();
            Spot spot = spotRepository.findBySpotName(spotName);
            spots.remove(spot);
            travel.setSpots(spots);
            travelRepository.save(travel);
        }
    }
}
