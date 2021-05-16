package com.amsidh.mvc.reactivedemo.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class Person {
    private Integer personId;
    private String firstName;
    private String lastName;
    private Integer age;
}
