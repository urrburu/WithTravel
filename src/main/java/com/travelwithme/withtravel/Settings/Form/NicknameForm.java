package com.travelwithme.withtravel.Settings.Form;

import com.travelwithme.withtravel.Account.Account;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NicknameForm {

    private String newNickname;

    public NicknameForm(Account account){
        this.newNickname = account.getNickname();
    }
}
