package com.travel.city.service;

import com.travel.city.domain.City;
import com.travel.city.domain.CityTravelGetDto;
import com.travel.city.domain.Travel;

import java.util.*;

public interface CityService {

    List<CityTravelGetDto> getCityList(String memberId);
    int setCity(City city);

}
