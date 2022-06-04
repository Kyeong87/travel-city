package com.travel.city.service;

import com.travel.city.domain.Travel;
import com.travel.city.repository.TravelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Log
@RequiredArgsConstructor
public class TravelService {

    @Autowired
    TravelRepository travelRepository;

    @Transactional
    public List<Travel> getTravelList(String memberId){
        List<Travel> result = travelRepository.findByMemberId(memberId);
        if(result == null){
            return new ArrayList<>();
        }
        return result;
    }

    @Transactional
    public double saveTravel(Travel travelForm, int cityId, String memberId) {

        List<Travel> checkTravel = travelRepository.findByCityIdAndMemberId(cityId, memberId);
        if(checkTravel.size()>0) return 405;

        LocalDateTime now = LocalDateTime.now();

        if(travelForm.getStartDate().isBefore(LocalDate.now())) { // 여행 날짜가 오늘보다 전일일 경우
            return 406;
        }

        Travel travel = new Travel();
        travel.setMemberId(memberId);
        travel.setCityId(cityId);
        travel.setStartDate(travelForm.getStartDate());
        travel.setEndDate(travelForm.getEndDate());
        travel.setRegisterDate(now);
        travelRepository.save(travel);

        return 200;
    }
}
