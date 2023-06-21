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
    private String placeContent;
    private double avgRating;
    private String placeTitle;
    private String placeType;
    private int reviewCnt;
    private int price;
    private String imageUrl;
    private int memberId;
    private String address;
}