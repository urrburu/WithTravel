package com.travelwithme.withtravel.UnitTest.Entity;


import com.travelwithme.withtravel.Account.Account;
import com.travelwithme.withtravel.Account.Address;
import com.travelwithme.withtravel.Enrollment.Enrollment;
import com.travelwithme.withtravel.Tag.Tag;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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



}
