package com.travel.city.controller;

import com.travel.city.domain.Travel;
import com.travel.city.service.TravelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Log
@RequiredArgsConstructor
public class TravelController {

    @Autowired
    TravelService travelService;

    // 여행 조회
    @GetMapping("/travel/{memberId}")
    public ResponseEntity<List<Travel>> getTravel(@PathVariable("memberId") String memberId) {

        List<Travel> result = travelService.getTravelList(memberId);
        if(result.size() == 0){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 여행 등록
    @PostMapping("/travel/{cityId}/{memberId}")
    public ResponseEntity<Map<String, String>> setVacation(@RequestBody Travel travel, @PathVariable("cityId") int cityId, @PathVariable("memberId") String memberId) {

        double result =  travelService.saveTravel(travel, cityId, memberId);
        Map<String, String> response = new HashMap<>();

        if(result == 405) {
            response.put("message", "하나의 도시에는 하나의 여행만 등록가능합니다.");
            return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
        }else if(result == 406) {
            response.put("message", "여행 시작날짜를 확인해주세요.");
            return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
        }else {
            response.put("message", "여행 등록 완료되었습니다.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
}
