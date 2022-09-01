package com.travelwithme.withtravel;

import com.travelwithme.WithAccount;
import com.travelwithme.withtravel.Account.AccountRepository;
import com.travelwithme.withtravel.Plan.PlanRepository;
import com.travelwithme.withtravel.Travel.Travel;
import com.travelwithme.withtravel.Travel.TravelController;
import com.travelwithme.withtravel.Travel.TravelRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class TravelControllerTest {

    @Autowired    MockMvc mockMvc;
    @Autowired    TravelController travelController;
    @Autowired    TravelRepository travelRepository;
    @Autowired    PlanRepository planRepository;
    @Autowired AccountRepository accountRepository;


    @BeforeEach
    public void BeforeTest(){


    }

    @AfterEach
    public void AfterTest(){
        travelRepository.deleteAll();
        //accountRepository.deleteAll();
        //딜리트 올 함수 작성하기 전까지는 수동으로 하나하나 테스트 할것.....
    }
    @WithAccount(value = "chanhwi")
    @Test
    @DisplayName("여행화면 만들기 화면 Get 테스트")
    public void GetMakeTravel() throws Exception{
        mockMvc.perform(get("/travel/newTravel"))
                .andExpect(status().is2xxSuccessful());
    }
    @WithAccount(value = "chanhwi")
    @Test
    @DisplayName("여행화면 만들기 화면 Post 테스트")
    public void PostMakeTravel() throws Exception{
        mockMvc.perform(post("/travel/newTravel")
                .param("travelName", "부산여행")
                .param("Path","rightNowPusan")
                .param("shortDescription", "짧게 설명하기")
                .param("fullDescription", "길게길게길게설명하기 길게길게길게설명하기 길게길게길게설명하기 길게길게길게설명하기 길게길게길게설명하기 ")
                .param("startTime", LocalDateTime.now().plusWeeks(1).toString().substring(0,16))
                .param("endTime",LocalDateTime.now().plusWeeks(2).toString().substring(0,16))
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/travel/rightNowPusan"));
        Travel travel = travelRepository.findByPath("rightNowPusan");
        assertFalse(travel == null);
/*
테스트를 위해서 LocalDateTime의 포맷을 수정해서 맞춰줘야만 했다.
만약 통합테스트 시에 오류가 일어날 경우 TravelForm을 수정하는게 좋을 듯.

 */
    }
    @WithAccount(value = "chanhwi")
    @Test
    @DisplayName("여행화면 만들기 잘못된 이름 Post 테스트")
    public void PostWrongNameMakeTravel() throws Exception{
        mockMvc.perform(post("/travel/newTravel")
                        .param("travelName", "행부산여행부산여행부산여행부산여행부산여행부산여행부산여행부산여")
                        .param("Path","rightNowPusan")
                        .param("shortDescription", "짧게 설명하기")
                        .param("fullDescription", "길게길게길게설명하기 길게길게길게설명하기 길게길게길게설명하기 길게길게길게설명하기 길게길게길게설명하기 ")
                        .param("startTime", LocalDateTime.now().plusWeeks(1).toString())
                        .param("endTime",LocalDateTime.now().plusWeeks(2).toString())
                        .with(csrf()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("/travel/makeTravel"));
        Travel travel = travelRepository.findByPath("rightNowPusan");
        assertTrue(travel == null);
    }
    @WithAccount(value = "chanhwi")
    @Test
    @DisplayName("여행화면 만들기 잘못된 Path Post 테스트")
    public void PostWrongPathMakeTravel() throws Exception{
        mockMvc.perform(post("/travel/newTravel")
                        .param("travelName", "부산여행")
                        .param("Path","한글로된Path한글로된Path한글로된Path")
                        .param("shortDescription", "짧게 설명하기")
                        .param("fullDescription", "길게길게길게설명하기 길게길게길게설명하기 길게길게길게설명하기 길게길게길게설명하기 길게길게길게설명하기 ")
                        .param("startTime", LocalDateTime.now().plusWeeks(1).toString())
                        .param("endTime",LocalDateTime.now().plusWeeks(2).toString())
                        .with(csrf()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("/travel/makeTravel"));
        Travel travel = travelRepository.findByPath("한글로된Path한글로된Path한글로된Path");
        assertTrue(travel == null);
    }


}
