package com.travel.city.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Travel {
    @Id
    private Long id;

    private String memberId;
    private int cityId;

    private LocalDate startDate;
    private LocalDate endDate;

    private LocalDateTime registerDate;
}