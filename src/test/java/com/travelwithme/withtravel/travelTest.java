package com.travelwithme.withtravel;

import com.travelwithme.WithAccount;
import com.travelwithme.withtravel.Account.AccountService;
import com.travelwithme.withtravel.Repository.AccountRepository;
import com.travelwithme.withtravel.Settings.SettingService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class travelTest {

    @Autowired MockMvc mockMvc;
    @Autowired AccountRepository accountRepository;
    @Autowired AccountService accountService;
    @Autowired SettingService settingService;

    
    @AfterEach
    void afterEach() {
        accountRepository.deleteAll();
    }

    @WithAccount(value = "chanhwi")
    @Test
    @DisplayName("여행 만들기 - 입력값 정상")
    public void travelMakeTest() throws Exception{
        mockMvc.perform(post("/travel/make")
                .param("travelName", "chanhwiTravel")
                .param("shortDescription", "안녕하세요 위드트래블 테스트 여행입니다.")
                .param("fullDescription", "안녕하세요 위드트래블 테스트 여행입니다. 즐거운 여행 되세요. ")
                //.param("startTime", "")
                //.param("endTime", "")
                .with(csrf()))
                .andExpect(status().is3xxRedirection());

        
    }
}
