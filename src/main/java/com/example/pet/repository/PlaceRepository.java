package com.example.pet.repository;

import com.example.pet.domain.place.Place;
import com.example.pet.dto.place.PlaceDto;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Integer> {
    List<Place> findByPlaceType(String placeType);

    @Query("SELECT p FROM Place p WHERE p.region.regionId = :regionId")
    List<Place> findByRegionId(@Param("regionId") int regionId);

    @Query("SELECT p FROM Place p WHERE p.region.regionId = :regionId and p.placeType = :placeType")
    List<Place> findByPlaceTypeAndRegionId(@Param("placeType") String placeType, @Param("regionId") int regionId);
}
