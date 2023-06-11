package com.example.pet.repository;

import com.example.pet.domain.place.Place;
import com.example.pet.domain.place.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Integer> {
    List<Place> findByPlaceType(String placeType);

    @Query("SELECT p FROM Place p WHERE p.region.regionId = :regionId")
    List<Place> findByRegionId(@Param("regionId") int regionId);
}
