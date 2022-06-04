package com.travel.city.mapper;

import com.travel.city.domain.City;
import com.travel.city.domain.CityTravelGetDto;
import com.travel.city.domain.Travel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface CityMapper {
    List<CityTravelGetDto> getCityList(String memberId);
    int setCity(City city);
}