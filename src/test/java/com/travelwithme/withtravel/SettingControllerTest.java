package com.travelwithme.withtravel;

import com.travelwithme.WithAccount;
import com.travelwithme.withtravel.Account.Account;
import com.travelwithme.withtravel.Account.Form.SignUpForm;
import com.travelwithme.withtravel.Repository.AccountRepository;
import com.travelwithme.withtravel.Service.AccountService;
import com.travelwithme.withtravel.Settings.Profile;
import com.travelwithme.withtravel.Settings.SettingService;
import com.travelwithme.withtravel.Settings.SettingsController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithSecurityContext;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class SettingControllerTest {

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
    @DisplayName("프로필 수정하기 - 입력값 정상")
    public void modifyProfileTestNormal() throws Exception{

        mockMvc.perform(post("/settings/profile")
                .param("bio", "안녕하세요")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/profile/chanhwi"))
                .andExpect(flash().attributeExists("message"));

        Account account = accountRepository.findByNickname("chanhwi");
        assertEquals("안녕하세요", account.getBio());



    }

    @WithAccount(value = "chanhwi")
    @Test
    @DisplayName("프로필 수정하기 - 입력값 비정상적으로 긴 바이오")
    public void modifyProfileTestTooLong() throws Exception{

        mockMvc.perform(post("/settings/profile")
                        .param("bio", "안녕하세요 안녕하세요 안녕하세요 안녕하세요 안녕하세요 안녕하세요 안녕하세요 안녕하세요안녕하세요" +
                                "안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요 안녕하세요 안녕하세요 안녕하세요 안녕하세요 안녕하세요 안녕하세요 " +
                                "안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("Profile/modifyProfile"))
                .andExpect(model().attributeExists("account"))
                .andExpect(model().attributeExists("profile"))
                .andExpect(model().hasErrors());

        Account account = accountRepository.findByNickname("chanhwi");
        assertNull(account.getBio());



    }


}
