package com.example.pet.repository;

import com.example.pet.domain.place.Place;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Integer> {
    List<Place> findByPlaceType(String placeType);

    //지역별 조회
    @Query("SELECT p FROM Place p WHERE p.region.regionId = :regionId")
    Page<Place> findByRegionId(@Param("regionId") Integer regionId, Pageable pageable);

    //장소타입별 조회
    @Query("SELECT p FROM Place p WHERE p.placeType = :placeType")
    Page<Place> findByPlaceType(@Param("placeType") String placeType, Pageable pageable);

    //keyword로 조회
    @Query("SELECT p FROM Place p WHERE UPPER(p.placeTitle) LIKE REPLACE(UPPER(CONCAT('%', :keyword, '%')), ' ', '')")
    Page<Place> findByPlaceTitle(@Param("keyword") String keyword, Pageable pageable);

    //지역 & 장소타입별 조회
    @Query("SELECT p FROM Place p WHERE p.region.regionId = :regionId and p.placeType = :placeType")
    Page<Place> findByPlaceTypeAndRegionId(@Param("placeType") String placeType, @Param("regionId") Integer regionId, Pageable pageable);


    //지역 & 키워드로 조회
    @Query("SELECT p FROM Place p WHERE p.region.regionId = :regionId and UPPER(p.placeTitle) LIKE REPLACE(UPPER(CONCAT('%', :keyword, '%')), ' ', '')")
    Page<Place> findByRegionIdAndPlaceTitle(@Param("regionId") Integer regionId, @Param("keyword") String keyword, Pageable pageable);


    //장소타입 & 키워드로 조회
    @Query("SELECT p FROM Place p WHERE p.placeType = :placeType and UPPER(p.placeTitle) LIKE REPLACE(UPPER(CONCAT('%', :keyword, '%')), ' ', '')")
    Page<Place> findByPlaceTypeAndPlaceTitle(@Param("placeType") String placeType, @Param("keyword") String keyword, Pageable pageable);


    //지역 & 장소 타입 & keyword로 조회
    @Query("SELECT p FROM Place p WHERE p.region.regionId = :regionId and p.placeType = :placeType and UPPER(p.placeTitle) LIKE REPLACE(UPPER(CONCAT('%', :keyword, '%')), ' ', '')")
    Page<Place> findByPlaceTypeAndRegionIdAndPlaceTitle(@Param("placeType") String placeType, @Param("regionId") Integer regionId, @Param("keyword") String keyword, Pageable pageable);


    List<Place> findTop5ByOrderByAvgRatingDesc();

    Page<Place> findAll(Pageable pageable);

}
