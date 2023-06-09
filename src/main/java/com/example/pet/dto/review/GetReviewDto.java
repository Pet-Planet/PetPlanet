package com.example.pet.dto.review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetReviewDto {

    private Long memberId;

    private String placeTitle;

    private String nickName;

    private int rating; //평점 1~5

    private String content; //리뷰 내용

    private LocalDateTime writtenDate;


}
