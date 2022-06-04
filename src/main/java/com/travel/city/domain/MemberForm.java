package com.travel.city.domain;

import lombok.Data;

@Data
public class MemberForm {
    private String id;
    private String password;
    private String name;
    private double vacationCount;
}
