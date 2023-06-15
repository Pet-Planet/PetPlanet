package com.example.pet.service;

import com.example.pet.domain.place.Place;
import com.example.pet.domain.place.Region;
import com.example.pet.dto.place.PlaceDetailDto;
import com.example.pet.dto.place.PlaceDto;
import com.example.pet.dto.region.RegionDto;
import com.example.pet.repository.PlaceRepository;
import com.example.pet.repository.RegionRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlaceService {
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private RegionRepository regionRepository;

    //전체 장소 조회
    public List<PlaceDto> getAllPlaces() {
        List<Place> placeList = placeRepository.findAll();
        List<PlaceDto> placeDtoList = new ArrayList<>();

        for (Place place : placeList) {
            PlaceDto placeDto = new PlaceDto();
            placeDto.setPlaceId(place.getPlaceId());
            placeDto.setAvgRating(place.getAvgRating());
            placeDto.setReviewCnt(place.getReviewCnt());
            placeDto.setPlaceTitle(place.getPlaceTitle());
            placeDto.setPlaceType(place.getPlaceType());
            placeDto.setImageUrl(place.getImageUrl());

            placeDtoList.add(placeDto);
        }

        return placeDtoList;
    }

    // 장소 상세 조회하기
    public PlaceDetailDto getPlaceDetail(int placeId) {
        Optional<Place> placeResult = placeRepository.findById(placeId);
        if (placeResult.isPresent()) {
            Place place = placeResult.get();

            RegionDto regionDto = null;
            Optional<Region> regionResult = regionRepository.findById(place.getRegion().getRegionId());
            if (regionResult.isPresent()) {
                Region region = regionResult.get();
                regionDto = new RegionDto();
                regionDto.setRegionName(region.getRegionName());
            }
            String regionName = (regionDto != null) ? regionDto.getRegionName() : null;

            // 장소 상세 정보 DTO를 설정합니다.
            PlaceDetailDto placeDetailDto = new PlaceDetailDto();
            placeDetailDto.setPlaceId(place.getPlaceId());
            placeDetailDto.setPlaceContent(place.getPlaceContent());
            placeDetailDto.setAvgRating(place.getAvgRating());
            placeDetailDto.setPlaceTitle(place.getPlaceTitle());
            placeDetailDto.setPlaceType(place.getPlaceType());
            placeDetailDto.setReviewCnt(place.getReviewCnt());
            placeDetailDto.setPrice(place.getPrice());
            placeDetailDto.setImageUrl(place.getImageUrl());
            placeDetailDto.setRegionName(regionName);

            return placeDetailDto;
        }
        return null;
    }
}