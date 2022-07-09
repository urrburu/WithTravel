package com.travelwithme.withtravel;

import com.travelwithme.WithAccount;
import com.travelwithme.withtravel.Account.Account;
import com.travelwithme.withtravel.Account.AccountRepository;
import com.travelwithme.withtravel.Spot.Spot;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Point;

@SpringBootTest
@AutoConfigureMockMvc
public class SpotEntityTest {

    @Autowired    AccountRepository accountRepository;

    @WithAccount(value = "chanhwi"    )
    @Test
    @DisplayName("Spot Entity 생성")
    public void SpotBuild(){
        Account account = accountRepository.findByNickname("chanhwi");
        Point point = new Point(0.000, 0.0000);
        Spot hotel = new Spot().hotel("aaaaa", "bbbbbb", point, account);
        Spot restaurant = new Spot().restaurant("aaaaa", "bbbbbb", point, account);
    }



}