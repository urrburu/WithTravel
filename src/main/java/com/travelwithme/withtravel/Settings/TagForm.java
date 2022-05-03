package com.travelwithme.withtravel.Settings;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class TagForm {

    @Length(max=20)
    private String tagName;

}
