package com.travelwithme.withtravel;

import com.travelwithme.WithAccount;
import com.travelwithme.withtravel.Account.Account;
import com.travelwithme.withtravel.Account.AccountService;
import com.travelwithme.withtravel.Repository.AccountRepository;
import com.travelwithme.withtravel.Repository.TravelRepository;
import com.travelwithme.withtravel.Settings.SettingService;
import com.travelwithme.withtravel.Travel.Travel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TravelControllerTest {


    @Autowired    AccountRepository accountRepository;
    @Autowired    AccountService accountService;
    @Autowired    TravelRepository travelRepository;
    @Autowired    SettingService settingService;
    @Autowired    MockMvc mockMvc;


    @AfterEach
    void afterEach() {

        travelRepository.deleteAll();
        accountRepository.deleteAll();
    }

    @WithAccount(value = "chanhwi")
    @Test @DisplayName("여행 만들기 - 뷰 정상적으로 보임")
    public void makeTravelView() throws Exception{
        mockMvc.perform(get("/travel/make"))
                .andExpect(status().is2xxSuccessful());
    }

    @WithAccount(value = "chanhwi")
    @Test @DisplayName("여행 만들기 - 입력값 정상")
    public void makeTravelTestNormal() throws Exception{
        mockMvc.perform(post("/travel/make")
                        .param("travelName", "forBusan")
                        .param("shortDescription", "short description of a travel")
                        .param("fullDescription", "full description of a travel full description of a travel full description of a travelfull description of a travelfull description of a travelfull description of a travel full description of a travelfull description of a travel")
                        //.param("startTime", "2022-06-07T17:03:27")
                        //.param("endTime","2022-06-25T17:03")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection());
        Travel travel = travelRepository.findByTravelName("forBusan");
        assertNotNull(travel);
    }

    @WithAccount(value = "chanhwi")
    @Test
    @DisplayName("여행 만들기 - 입력값 너무 긴 짧은 설명")
    public void makeTravelTestTooLongShortDescription() throws Exception{
        mockMvc.perform(post("/travel/make")
                        .param("travelName", "forBusan")
                        .param("shortDescription", "short description of a travel short description of a travel short description of a travel short description of a travel short description of a travel")
                        .param("fullDescription", "full description of a travel")
                        //.param("startTime", "2022-06-07T17:03:27")
                        //.param("endTime","2022-06-25T17:03")
                        .with(csrf()))
                .andExpect(status().is4xxClientError());
        Travel travel = travelRepository.findByTravelName("forBusan");
        assertNull(travel);
    }

}
