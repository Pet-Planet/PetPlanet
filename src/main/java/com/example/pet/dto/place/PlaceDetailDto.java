package com.example.pet.dto.place;
import com.example.pet.domain.place.Region;
import com.example.pet.domain.review.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String address;
    private Region region;
    private List<Review> reviews;
}