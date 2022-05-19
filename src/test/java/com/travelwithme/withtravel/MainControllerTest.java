package com.travelwithme.withtravel;

import com.travelwithme.withtravel.Account.Form.SignUpForm;
import com.travelwithme.withtravel.Repository.AccountRepository;
import com.travelwithme.withtravel.Account.AccountService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MainControllerTest {
    @Autowired    MockMvc mockMvc;
    @Autowired    AccountRepository accountRepository;
    @Autowired    AccountService accountService;

    @BeforeEach
    void beforeEach() {
        SignUpForm signUpForm= new SignUpForm();
        signUpForm.setNickname("chanhwi");
        signUpForm.setEmail("chanhwi@email.com");
        signUpForm.setPassword("123123123");
        accountService.processNewAccount(signUpForm);
    }
    @AfterEach
    void afterEach() {
        accountRepository.deleteAll();
    }
    //AOP 개념을 사용하는 동작
    @DisplayName("이메일 로그인 구현")
    @Test
    void LoginCheck() throws Exception{

        mockMvc.perform(post("/login")
                        .param("username", "chanhwi@email.com")
                        .param("password", "123123123")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"))
                .andExpect(authenticated().withUsername("chanhwi"));
    }
    @DisplayName("로그아웃 구현")
    @Test
    void LogoutCheck() throws Exception{
        mockMvc.perform(post("/logout")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"))
                .andExpect(unauthenticated());
    }
    @DisplayName("닉네임 로그인 구현")
    @Test
    void LoginNicknameCheck() throws Exception{
        mockMvc.perform(post("/logout")
                .with(csrf()));
        mockMvc.perform(post("/login")
                        .param("username", "chanhwi")
                        .param("password", "123123123")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"))
                .andExpect(authenticated());
    }

}
