package com.travelwithme.withtravel;

import com.travelwithme.withtravel.Account.Account;
import com.travelwithme.withtravel.Repository.AccountRepository;
import com.travelwithme.withtravel.Account.AccountService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.web.servlet.MockMvc;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class AccountServiceTest {

    @Autowired MockMvc mockMvc;
    @Autowired AccountRepository accountRepository;
    @Autowired
    AccountService accountService;
    @MockBean JavaMailSender javaMailSender;

    @DisplayName("회원가입 처리 - 입력값 정상")
    @Test
    void signUpSubmit_with_correct_input() throws Exception
    {
        mockMvc.perform(post("/sign-up")
                .param("nickname", "chanhwi")
                .param("email", "chanhwi@email.com")
                .param("password", "123123123")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));

        Account account = accountRepository.findByEmail("chanhwi@email.com");
        assertNotNull(account);
        assertNotEquals(account.getPassword(), "123123123");
        then(javaMailSender).should().send(any(SimpleMailMessage.class));


    }

    @DisplayName("이메일 확인 처리 - 입력값 정상")
    @Test
    void CheckEmailOKPattern() throws Exception{
        /*mockMvc.perform(post("/sign-up")
                        .param("nickname", "chanhwi")
                        .param("email", "chanhwi@email.com")
                        .param("password", "123123123")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));*/
        Account account = accountRepository.findByEmail("chanhwi@email.com");
        mockMvc.perform(get("/check-email-token")
                .param("token",account.getEmailCheckToken())
                .param("email", "chanhwi@email.com"))
                .andExpect(status().isOk())
                .andExpect(model().attributeDoesNotExist("error"))
                .andExpect(model().attributeExists("nickname"))
                .andExpect(view().name("account/checkedEmail"))
                .andExpect(authenticated());
    }
    @DisplayName("이메일 확인 처리 - 입력값 비정상 토큰이 잘못됨")
    @Test
    void CheckTokenFailPattern() throws Exception{
        /*mockMvc.perform(post("/sign-up")
                        .param("nickname", "chanhwi")
                        .param("email", "chanhwi@email.com")
                        .param("password", "123123123")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));*/
        Account account = accountRepository.findByEmail("chanhwi@email.com");
        mockMvc.perform(get("/check-email-token")
                        .param("token","wrenoikweropikjnj")
                        .param("email", "chanhwi@email.com"))
                .andExpect(status().isOk())
                .andExpect(model().attributeDoesNotExist("nickname"))
                .andExpect(model().attributeExists("error"))
                .andExpect(view().name("account/checkedEmail"))
                .andExpect(unauthenticated());
    }
    @DisplayName("이메일 확인 처리 - 입력값 비정상 이메일이 잘못됨")
    @Test
    void CheckEmailFailPattern() throws Exception{
        /*mockMvc.perform(post("/sign-up")
                        .param("nickname", "chanhwi")
                        .param("email", "chanhwi@email.com")
                        .param("password", "123123123")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));*/
        Account account = accountRepository.findByEmail("chanhwi@email.com");
        mockMvc.perform(get("/check-email-token")
                        .param("token",account.getEmailCheckToken())
                        .param("email", "WrongEmail@email.com"))
                .andExpect(status().isOk())
                .andExpect(model().attributeDoesNotExist("nickname"))
                .andExpect(model().attributeExists("error"))
                .andExpect(view().name("account/checkedEmail"))
                .andExpect(unauthenticated());
    }

}
