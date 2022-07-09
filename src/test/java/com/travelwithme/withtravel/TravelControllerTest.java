package com.travelwithme.withtravel;

import com.travelwithme.WithAccount;
import com.travelwithme.withtravel.Plan.PlanRepository;
import com.travelwithme.withtravel.Travel.TravelController;
import com.travelwithme.withtravel.Travel.TravelRepository;
import com.travelwithme.withtravel.Travel.TravelService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TravelControllerTest {

    @Autowired    MockMvc mockMvc;
    @Autowired    TravelController travelController;
    @Autowired    TravelRepository travelRepository;
    @Autowired    PlanRepository planRepository;


    @BeforeEach
    public void BeforeTest(){


    }

    @AfterEach
    public void AfterTest(){


    }
    @WithAccount(value = "chanhwi")
    @Test
    @DisplayName("여행화면 만들기 화면 Get 테스트")
    public void GetMakeTravel() throws Exception{

        mockMvc.perform(get("travel/newTravel"))
                .andExpect(status().is2xxSuccessful());
    }
    @WithAccount(value = "chanhwi")
    @Test
    @DisplayName("여행화면 만들기 화면 Post 테스트")
    public void PostMakeTravel() throws Exception{

    }
    @WithAccount(value = "chanhwi")
    @Test
    @DisplayName("여행화면 만들기 잘못된 이름 Post 테스트")
    public void PostWrongNameMakeTravel() throws Exception{

    }
    @WithAccount(value = "chanhwi")
    @Test
    @DisplayName("여행화면 만들기 잘못된 Path Post 테스트")
    public void PostWrongPathMakeTravel() throws Exception{

    }


}
