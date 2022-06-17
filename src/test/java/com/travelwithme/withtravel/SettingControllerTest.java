package com.travelwithme.withtravel;

import com.travelwithme.WithAccount;
import com.travelwithme.withtravel.Account.Account;
import com.travelwithme.withtravel.Account.AccountRepository;
import com.travelwithme.withtravel.Account.AccountService;
import com.travelwithme.withtravel.Settings.SettingService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
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
    @Autowired PasswordEncoder passwordEncoder;

    @AfterEach
    void afterEach() {
        accountRepository.deleteAll();
    }

    @WithAccount(value = "chanhwi")
    @Test
    @DisplayName("프로필 수정하기 - 입력값 정상")
    public void modifyProfileTestNormal() throws Exception{
        Account account = accountRepository.findByNickname("chanhwi");
        mockMvc.perform(post("/settings/profile")
                .param("bio", "안녕하세요")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/profile/"+account.getNickname()))
                .andExpect(flash().attributeExists("message"));

        Account Nickaccount = accountRepository.findByNickname("chanhwi");
        assertEquals("안녕하세요", Nickaccount.getBio());



    }

    @WithAccount(value = "chanhwi")
    @Test
    @DisplayName("프로필 수정하기 - 고봉밥 바이오")
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
    @WithAccount(value = "chanhwi")
    @Test
    @DisplayName("비밀번호 수정하기 - 정상")
    public void modifyPassword() throws Exception{

        mockMvc.perform(post("/settings/password")
                        .param("password", "123456789")
                        .param("passwordCheck", "123456789")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/settings/password"))
                .andExpect(flash().attributeExists("message"));

        Account account = accountRepository.findByNickname("chanhwi");
        assertTrue(passwordEncoder.matches("123456789", account.getPassword()));
    }

    @WithAccount(value = "chanhwi")
    @Test
    @DisplayName("알림 설정 변경하기  - 정상")
    public void NotificationModifyNormal() throws Exception{
        boolean T = true;
        mockMvc.perform(post("/settings/notification")
                        .param("travelCreatedByWeb", "true")
                        .param("travelEnrollmentResultByWeb", "true")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/settings/notification"))
                .andExpect(flash().attributeExists("message"));

        Account account = accountRepository.findByNickname("chanhwi");
        assertEquals(true, account.isTravelCreatedByWeb());
    }
    @WithAccount(value = "chanhwi")
    @Test
    @DisplayName("닉네임 변경하기  - 정상")
    public void nicknameNormal() throws Exception{
        String newNickname = "chanchan";
        mockMvc.perform(post("/settings/account")
                        .param("newNickname", newNickname)
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/settings/account"))
                .andExpect(flash().attributeExists("message"));

        Account account = accountRepository.findByEmail("chanhwi@email.com");
        assertEquals(account.getNickname(), newNickname);
    }

}
