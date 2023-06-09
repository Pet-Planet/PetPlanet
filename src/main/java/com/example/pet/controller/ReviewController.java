package com.example.pet.controller;

import com.example.pet.dto.review.GetReviewDto;
import com.example.pet.dto.review.ReviewDto;
import com.example.pet.dto.review.ReviewEditDto;
import com.example.pet.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
@RestController
@RequiredArgsConstructor
@Slf4j
public class ReviewController {


    private final ReviewService reviewService;


    /*
    리뷰 작성 폼
     */
    @GetMapping("/review")
    public String reviewForm(){
        return "reviewForm";
    }

    /*
    리뷰 작성 API
     */

    @PostMapping("/review")   //restApi
    public String createReview(@RequestBody ReviewDto reviewDto){

       reviewService.createReview(reviewDto);

       return reviewDto.getMemberId() + " success";
    }

    /*
    리뷰 수정 API
     */
    @PutMapping("/review/edit/{reviewId}")
    public String editReview(@PathVariable int reviewId, @RequestBody ReviewEditDto reviewDto){

        reviewService.editReview(reviewId, reviewDto);

        return reviewId + " success";

//        return "redirect:/reviewList";
    }


        /*
    내가 쓴 리뷰 조회 API
     */

    @GetMapping("/myPage/review/{memberId}")
    public List<GetReviewDto> getReview(@PathVariable int memberId){

        return reviewService.getReviewList(memberId);

//        return "redirect:/reviewList";
    }

    /*
    리뷰 삭제
     */
    @DeleteMapping("/review/{reviewId}")
    public int deleteReview(@PathVariable int reviewId){

        int id = reviewService.deleteReview(reviewId);

        return id;
    }


}
