package com.example.pet.service;

import com.example.pet.domain.place.Place;
import com.example.pet.domain.place.Region;
import com.example.pet.domain.review.Review;
import com.example.pet.dto.place.PlaceDetailDto;
import com.example.pet.dto.place.PlaceDto;
import com.example.pet.dto.region.RegionDto;
import com.example.pet.dto.review.ReviewEditDto;
import com.example.pet.repository.PlaceRepository;
import com.example.pet.repository.RegionRepository;
import com.example.pet.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlaceService {
    private final PlaceRepository placeRepository;
    private final RegionRepository regionRepository;
    private final ReviewRepository reviewRepository;

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
            placeDetailDto.setAddress(place.getAddress());
            placeDetailDto.setRegion(place.getRegion());

            // 리뷰 DTO 리스트를 설정합니다.
            List<Review> reviews = place.getReviews();
            List<ReviewEditDto> reviewDtos = new ArrayList<>();
            for (Review review : reviews) {
                ReviewEditDto reviewDto = new ReviewEditDto();
                reviewDto.setId(review.getId());
                reviewDto.setMemberId(review.getMember().getMemberId());
                reviewDto.setPlaceId(review.getPlace().getPlaceId());
                reviewDto.setNickName(review.getMember().getNickname());
                reviewDto.setRating(review.getRating());
                reviewDto.setContent(review.getContent());
                reviewDto.setWrittenDate(review.getLastModifiedDate());
                reviewDtos.add(reviewDto);
            }
            placeDetailDto.setReviews(reviewDtos);
            return placeDetailDto;
        }
        return null;
    }

    //전체 장소 조회
    public List<PlaceDto> getAllPlaces() {
        List<Place> placeList = placeRepository.findAll();
        updateReviewStats(placeList);
        return convertToPlaceDtoList(placeList);
    }

    //타입별 장소 조회
    public List<PlaceDto> getPlacesByPlaceType(String placeType) {
        List<Place> placeList = placeRepository.findByPlaceType(placeType);
        updateReviewStats(placeList);
        return convertToPlaceDtoList(placeList);
    }

    // 지역별 장소 조회
    public List<PlaceDto> getPlacesByRegionId(int regionId) {
        List<Place> placeList = placeRepository.findByRegionId(regionId);
        updateReviewStats(placeList);
        return convertToPlaceDtoList(placeList);
    }

    //타입 & 지역별 장소 조회
    public List<PlaceDto> getPlacesByTypeAndRegion(String placeType, Integer regionId) {
        List<Place> placeList = placeRepository.findByPlaceTypeAndRegionId(placeType, regionId);
        updateReviewStats(placeList);
        return convertToPlaceDtoList(placeList);
    }

    // Place 리스트를 PlaceDto 리스트로 변환하는 메서드
    private List<PlaceDto> convertToPlaceDtoList(List<Place> placeList) {
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
    // 각 장소의 리뷰 수와 평균 평점을 업데이트하는 메서드
    private void updateReviewStats(List<Place> placeList) {
        for (Place place : placeList) {
            Integer reviewCnt = 0;
            Double avgRating = 0.0;

            if (reviewRepository.countByPlaceId(place.getPlaceId()) == 0) {
                reviewCnt = 0;
                avgRating = 0.0;
            } else {
                reviewCnt = reviewRepository.countByPlaceId(place.getPlaceId());
                avgRating = reviewRepository.calculateAverageRatingByPlaceId(place.getPlaceId());
            }

            place.setReviewCnt(reviewCnt);
            place.setAvgRating(avgRating);
        }
        placeRepository.saveAll(placeList);
    }
}
