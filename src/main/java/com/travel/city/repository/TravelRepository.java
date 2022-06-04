package com.travel.city.repository;

import com.travel.city.domain.Travel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TravelRepository extends CrudRepository<Travel, String> {
    List<Travel> findByMemberId(String memberId);
    List<Travel> findByCityIdAndMemberId(int cityId, String memberId); // where cityId = ? and memberId = ?
}
