package com.travelwithme.withtravel;

import com.travelwithme.WithAccount;
import com.travelwithme.withtravel.Account.AccountService;
import com.travelwithme.withtravel.Account.AccountRepository;
import com.travelwithme.withtravel.Travel.TravelRepository;
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
        /*
        cascade option에 관해서. 이 의존관계는 트래블이 account 객체를 들고 있고
        * travel -> account 를 보는 일방적 관계였다.
        * 그렇기 때문에 account를 travel보다 먼저 삭제할 경우,
            travel이 보는 account가 삭제되면서, cascade옵션이 문제가 된 것이다.
        *
        * 그래서 여행을 먼저 지워주고 계정을 지워줘야한다.
        * 이유는 여행에서 계정정보를 들고 있으니까
        *
        */
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
