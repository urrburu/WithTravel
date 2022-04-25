package com.travelwithme.withtravel.Settings;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class Password {

    @Length(min=8, max=50)
    private String password;

    @Length(min = 8, max = 50)
    private String passwordCheck;
}
