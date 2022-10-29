package com.travelwithme.withtravel.Spot;

import com.travelwithme.withtravel.Account.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class SpotService {

    private final SpotRepository spotRepository;

    public Spot newSpot(Account account, SpotForm spotForm) {
        Spot spot = new Spot().builder()
                .spotName(spotForm.getSpotName())
                .firstMaker(account)
                .point(new Point(Double.parseDouble(spotForm.getLatitude()), Double.parseDouble(spotForm.getLongitude())))
                .shortDescription(spotForm.getShortDescription())
                .spotType(spotForm.getSpotType())
                .build();
        return spotRepository.save(spot);
    }

    public List<String> getWhiteList() {
        List<String> allSpots = spotRepository.findAll().stream().map(Spot::getSpotName).collect(Collectors.toList());
        return allSpots;
    }

    public List<Spot> searchBySpotName(String spotName) {
        return spotRepository.findBySpotNameContaining(spotName);
        //TODO 이름으로 장소 검색하는 기능 추가
    }
}
