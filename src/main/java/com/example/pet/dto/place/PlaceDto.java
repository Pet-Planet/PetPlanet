package com.example.pet.dto.place;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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