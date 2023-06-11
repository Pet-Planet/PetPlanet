package com.example.pet.dto.place;

import com.example.pet.domain.place.Place;
import com.example.pet.dto.region.RegionDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlaceDto {
    private int placeId;
    private String placeTitle;
    private String placeType;
    private double avgRating;
    private int reviewCnt;
    private String imageUrl;
    private String regionName;
}