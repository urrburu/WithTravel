package com.travelwithme.withtravel;

import com.travelwithme.WithAccount;
import com.travelwithme.withtravel.Account.Account;
import com.travelwithme.withtravel.Repository.AccountRepository;
import com.travelwithme.withtravel.Repository.TravelRepository;
import com.travelwithme.withtravel.Travel.Travel;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TravelControllerTest {

    @Autowired    AccountRepository accountRepository;
    @Autowired    TravelRepository travelRepository;
    @Autowired    MockMvc mockMvc;

    @WithAccount(value = "chanhwi")
    @Test
    @DisplayName("여행 만들기 - 입력값 정상")
    public void makeTravelTestNormal() throws Exception{
        Account account = accountRepository.findByNickname("chanhwi");
        mockMvc.perform(post("/travel/make")
                        .param("travelName", "forBusan")
                        .param("shortDescription", "aaaaaaaaaaaaaaaaaaa")
                        .param("fullDescription", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
                        .param("startTime", LocalDateTime.now().plusMonths(1).toString())
                        .param("endTime",LocalDateTime.now().plusMonths(2).toString())
                        .with(csrf()))
                .andExpect(status().is3xxRedirection());
        Travel travel = travelRepository.findByTravelName("forBusan");
        assertNotNull(travel);
    }

}
