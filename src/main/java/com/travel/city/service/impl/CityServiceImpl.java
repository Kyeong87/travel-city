package com.travel.city.service.impl;

import com.travel.city.domain.City;
import com.travel.city.domain.CityTravelGetDto;
import com.travel.city.domain.Travel;
import com.travel.city.mapper.CityMapper;
import com.travel.city.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityMapper cityMapper;

    @Override
    public List<CityTravelGetDto> getCityList(String memberId) {
        List<CityTravelGetDto> result = cityMapper.getCityList(memberId);
        if(result == null){
            return new ArrayList<>();
        }
        return result;
    }

    public int setCity(City city) {

        cityMapper.setCity(city);
        return 200;
    }
}
