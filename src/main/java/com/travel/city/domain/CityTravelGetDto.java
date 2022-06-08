package com.travel.city.domain;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class CityTravelGetDto {
    private int cityId;
    private LocalDate startDate;
    private LocalDate endDate;

    private String name;
    private String address;
    private String comment;
}
