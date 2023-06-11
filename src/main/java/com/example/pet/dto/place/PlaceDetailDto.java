package com.example.pet.dto.place;

import com.example.pet.dto.region.RegionDto;
import com.example.pet.dto.review.GetReviewDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlaceDetailDto {
    private int placeId;
    private String placeContent;
    private double avgRating;
    private String placeTitle;
    private String placeType;
    private int reviewCnt;
    private int price;
    private String imageUrl;
    private String regionName;
}
