package com.travel.city.controller;

import com.travel.city.domain.City;
import com.travel.city.domain.CityTravelGetDto;
import com.travel.city.service.CityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Log
@RequiredArgsConstructor
public class CityController {
    @Autowired
    CityService cityService;

    // 도시 조회
    @GetMapping("/city/{memberId}")
    public ResponseEntity<List<CityTravelGetDto>> getCity(@PathVariable("memberId") String memberId) {

        List<CityTravelGetDto> result = cityService.getCityList(memberId);
        if(result.size() == 0){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 도시 등록
    @PostMapping("/city")
    public ResponseEntity<Map<String, String>> setCity(@RequestBody City city) {

        double result =  cityService.setCity(city);
        Map<String, String> response = new HashMap<>();

        if(result == 405) {
            response.put("message", "도시 이름을 등록해주세요.");
            return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
        }else {
            response.put("message", "도시 등록이 완료되었습니다.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
}
