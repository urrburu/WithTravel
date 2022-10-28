package com.travelwithme.withtravel.UnitTest.Entity;


import com.travelwithme.withtravel.Account.Account;
import com.travelwithme.withtravel.Account.Address;
import com.travelwithme.withtravel.Enrollment.Enrollment;
import com.travelwithme.withtravel.Tag.Tag;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class AccountEntityTest {
    LocalDateTime localDateTime = LocalDateTime.now();

    @DisplayName("엔티티 생성")
    @Test
    void AccountMake(){
        final Account account = new Account();
    }

    @DisplayName("엔티티 빌더로 생성")
    @Test
    void AccountAllEntityMake(){

    }

    @DisplayName("빌더 사용")
    @Test
    void accountBuilderUse(){
        Account account = Account.builder()
                .nickname("nickname")
                .email("email@email.com")
                .password("password")
                .build();
        Assert.isInstanceOf(Account.class, account);
        Assertions.assertEquals(account.getNickname(), "nickname");
        Assertions.assertEquals(account.getEmail(), "email@email.com");
        Assertions.assertEquals(account.getPassword(), "password");
    }


}
