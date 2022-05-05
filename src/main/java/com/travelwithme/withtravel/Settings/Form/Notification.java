package com.travelwithme.withtravel.Settings.Form;

import com.travelwithme.withtravel.Account.Account;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class Notification {

    private boolean travelCreatedByWeb;

    private boolean travelEnrollmentResultByWeb;

    private boolean travelUpdatedByWeb;

    public Notification(Account account){
        this.travelCreatedByWeb = account.isTravelCreatedByWeb();
        this.travelEnrollmentResultByWeb = account.isTravelEnrollmentResultByWeb();
        this.travelUpdatedByWeb = account.isTravelUpdatedByWeb();
    }
}
