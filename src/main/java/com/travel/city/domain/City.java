package com.travel.city.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class City {
    private Long id;

    private String name;
    private String address;
    private String comment;

    private LocalDateTime registerDate;
}