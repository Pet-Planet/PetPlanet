package com.example.pet.dto.review;

import com.example.pet.domain.review.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {

    private int reviewId;

    private int memberId;

    private int placeId;
    private int rating; //평점 1~5

    private String content; //리뷰 내용


    //dto -> entity
    public Review toEntity(){

        return Review.builder()
                .rating(rating)
                .content(content)
                .build();
    }
}
