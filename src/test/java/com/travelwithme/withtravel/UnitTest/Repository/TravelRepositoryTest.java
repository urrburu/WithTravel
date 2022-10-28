package com.travelwithme.withtravel.UnitTest.Repository;

import com.travelwithme.withtravel.Account.Account;
import com.travelwithme.withtravel.Plan.Plan;
import com.travelwithme.withtravel.Tag.Tag;
import com.travelwithme.withtravel.Travel.Travel;
import com.travelwithme.withtravel.Travel.TravelRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@SpringBootTest
public class TravelRepositoryTest {

    @Autowired TravelRepository travelRepository;



    @DisplayName("저장소테스트 - 새로운 여행 저장 테스트")
    @Test
    void travelSaveTest() {
        Travel t = Travel.builder()
                .path("travelPath")
                .travelName("새로운 여행을 가자")
                .build();
        Travel findTravel = travelRepository.save(t);

        Assertions.assertEquals(findTravel, t);
        }
    }
