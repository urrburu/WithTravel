package com.travelwithme.withtravel.Travel;

import com.travelwithme.withtravel.Account.Account;
import com.travelwithme.withtravel.Spot.Spot;
import com.travelwithme.withtravel.Spot.SpotForm;
import com.travelwithme.withtravel.Spot.SpotRepository;
import com.travelwithme.withtravel.Tag.Tag;
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
            spot = spotRepository.save(new Spot(spotForm.getSpotName(), spotForm.getStartTime(), spotForm.getEndTime(),spotForm.getShortDescription(),
                     spotForm.getLatitude(), spotForm.getLongitude()));
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

    public void addTag(Travel travel, Tag newTag) {  travel.getTags().add(newTag);  }
}
