package com.travelwithme.withtravel;

import com.travelwithme.WithAccount;
import com.travelwithme.withtravel.Account.Account;
import com.travelwithme.withtravel.Account.AccountService;
import com.travelwithme.withtravel.Repository.AccountRepository;
import com.travelwithme.withtravel.Repository.TravelRepository;
import com.travelwithme.withtravel.Settings.SettingService;
import com.travelwithme.withtravel.Travel.Form.TravelForm;
import com.travelwithme.withtravel.Travel.Travel;
import com.travelwithme.withtravel.Travel.TravelService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TravelSettingControllerTest {

    @Autowired    AccountRepository accountRepository;
    @Autowired    AccountService accountService;
    @Autowired    TravelRepository travelRepository;
    @Autowired    SettingService settingService;
    @Autowired    MockMvc mockMvc;
    @Autowired    TravelService travelService;

    @BeforeEach
    public void beforeEach()throws Exception{
        Account account = accountRepository.findByNickname("chanhwi");
        if(account==null){
            throw new Exception("wrong make");
        }
        TravelForm travelForm = new TravelForm();
        travelForm.setTravelName("forBusan");
        travelForm.setStartTime(LocalDateTime.now().plusDays(3));
        travelForm.setEndTime(LocalDateTime.now().plusDays(7));
        travelForm.setShortDescription("go go go go go go");
        travelForm.setFullDescription("123456789123456789123456789123456789");
        travelService.newTravelMake(travelForm,account);
    }
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
    @Test
    @DisplayName("여행 본문 변경 화면 호출")
    private void DescriptionChangeView() throws Exception {
        mockMvc.perform(get("/travel/forBusan/settings/description"))
                .andExpect(status().is2xxSuccessful());

    }

    @WithAccount(value = "chanhwi")
    @Test
    @DisplayName("여행 본문 변경 예시")
    private void DescriptionChange() throws Exception {
        mockMvc.perform(post("/travel/forBusan/settings/description")
                .param("","")
                .param("","")
                .param("","")
                .param("","")
                .with(csrf()))
                .andExpect(status().is3xxRedirection());

    }
}
