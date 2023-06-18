package com.example.pet.dto.review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewEditDto {


    private int memberId;
    private int placeId;

    private int rating; //평점 1~5

    private String content; //리뷰 내용


}
