package com.travelwithme.withtravel.Travel;

import com.travelwithme.withtravel.Account.Account;
import com.travelwithme.withtravel.Plan.PlanRepository;
import com.travelwithme.withtravel.Plan.PlanService;
import com.travelwithme.withtravel.Spot.Spot;
import com.travelwithme.withtravel.Spot.SpotForm;
import com.travelwithme.withtravel.Spot.SpotRepository;
import com.travelwithme.withtravel.Spot.SpotService;
import com.travelwithme.withtravel.Tag.Tag;
import com.travelwithme.withtravel.Tag.TagRepository;
import com.travelwithme.withtravel.Travel.Form.TravelForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Service
@Transactional
@RequiredArgsConstructor
public class TravelService {

    private final TravelRepository travelRepository;
    private final SpotRepository spotRepository;
    private final SpotService spotService;
    private final TagRepository tagRepository;
    private final PlanRepository planRepository;
    private final PlanService planService;


    @Transactional
    public Travel newTravel(Account account,@Valid TravelForm travelForm) {
        Set<Account> accounts = new HashSet<>();
        accounts.add(account);
        Travel travel = Travel.builder()
                .path(travelForm.getPath())
                .travelName(travelForm.getTravelName())
                .managers(accounts)
                .startTime(travelForm.getStartTime())
                .endTime(travelForm.getEndTime())
                .shortDescription(travelForm.getShortDescription())
                .fullDescription(travelForm.getFullDescription())
                .recruiting(false)
                .published(false)
                .closed(false)
                .build();
        //빋드되고 나서 save로 반영되기 전에는 set함수를 쓸 수 없다.
        //널 포인터 익셉션이 발생한다.
        return travelRepository.save(travel);
    }
}
