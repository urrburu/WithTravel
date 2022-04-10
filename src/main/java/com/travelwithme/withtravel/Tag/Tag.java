package com.travelwithme.withtravel.Tag;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity@Getter@Setter
@Builder @AllArgsConstructor@NoArgsConstructor
@EqualsAndHashCode(of="ic")
public class Tag {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String title;
}
