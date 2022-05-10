package com.travelwithme.withtravel.Tag;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
public class TagForm {

    @Length(max=20)
    private String tagName;

}
