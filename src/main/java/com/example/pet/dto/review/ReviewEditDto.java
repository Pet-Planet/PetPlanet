package com.example.pet.dto.review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewEditDto {
    private int id; //리뷰 id
    private int memberId;
    private int placeId;
    private String nickName;
    private int rating; //평점 1~5
    private String content; //리뷰 내용
    private LocalDateTime writtenDate;
}
