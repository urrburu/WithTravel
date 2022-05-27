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
            // Todo 출발하지 않은 여행 9개를 리스트 순서에 따라서 갖고 오는건데
            if(travelList.size()==9)break;
        }
        return travelList;
    }

    public Travel newTravelMake(TravelForm travelForm, Account account){
        Set<Account> accounts = new HashSet<>();
        accounts.add(account);
        Travel travel = new Travel().builder()
                .managers(accounts)
                .travelName(travelForm.getTravelName())
                .shortDescription(travelForm.getShortDescription())
                .fullDescription(travelForm.getFullDescription())
                .startTime(travelForm.getStartTime())
                .endTime(travelForm.getEndTime())
                .publishedDateTime(LocalDateTime.now())
                .closedDateTime(LocalDateTime.now().plusDays(7))
                .closed(false)
                .published(true)
                .recruiting(true)
                .build();
        return travelRepository.save(travel);
    }

    public void addSpot(Travel travel, SpotForm spotForm) {
        Spot spot = spotRepository.findBySpotName(spotForm.getSpotName());
        if(spot ==null){
            spot = spotRepository.save(new Spot(spotForm.getSpotName(), spotForm.getStartTime(), spotForm.getEndTime(),spotForm.getShortDescription(), spotForm.getCost()));
        }
        travel.getSpots().add(spot);
        travel.costCalculate();
    }

    public void removeSpot(Travel travel, String spotName){
        travel = travelRepository.findByTravelName(travel.getTravelName());
        Spot spot = spotRepository.findBySpotName(spotName);
        if(travel != null && spot != null){travel.getSpots().remove(spot);        }
    }

    public void addMember(Travel travel, Account account) {travel.getMembers().add(account);}
}
