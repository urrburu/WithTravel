package com.travelwithme.withtravel.UnitTest.Repository;

import com.travelwithme.withtravel.Account.Account;
import com.travelwithme.withtravel.Account.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AccountRepositoryTest {

    @Autowired    AccountRepository accountRepository;

    @Test
    @DisplayName("새 계정 추가")
    public void addNewAccount(){
        Account account = Account.builder()
                .email("")
                .nickname("")
                .password("")
                .build();
        accountRepository.save(account);

        Account account1 = accountRepository.findByEmail("");
        Assertions.assertEquals(account, account1);
    }


}
