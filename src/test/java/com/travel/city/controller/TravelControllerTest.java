package com.travel.city.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travel.city.domain.Travel;
import lombok.extern.java.Log;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Log
@SpringBootTest
@AutoConfigureMockMvc
public class TravelControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper mapper;
    @Autowired private WebApplicationContext webContext;

    @BeforeEach
    public void initMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webContext)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))  // 필터 추가
                .alwaysDo(print()).build();
    }

    @Test
    @DisplayName("여행 등록 성공")
    void requestTravelSuccess() throws Exception {
        Travel travel = Travel.builder()
                .startDate(LocalDate.of(2022, 8, 24))
                .endDate(LocalDate.of(2022, 8, 24))
                .memberId("pyk")
                .cityId(1)
                .build();

        String data = mapper.writeValueAsString(travel);

        mockMvc.perform(post("/travel/{cityId}/{memberId}", 1,"pyk")
                .contentType(MediaType.APPLICATION_JSON)
                .content(data))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("여행 가져오기 테스트")
    void getTravelList() throws Exception {
        mockMvc.perform(get("/travel/{memberId}", "pyk"))
                .andExpect(status().isOk());
    }
}
