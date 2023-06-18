package com.example.pet.controller;

import com.example.pet.domain.review.Review;
import com.example.pet.dto.review.GetReviewDto;
import com.example.pet.dto.review.ReviewDto;
import com.example.pet.dto.review.ReviewEditDto;
import com.example.pet.repository.ReviewRepository;
import com.example.pet.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ReviewController {


    private final ReviewService reviewService;
    private final ReviewRepository reviewRepository;


    /*
    리뷰 작성 폼
     */
    @GetMapping("/review/{memberId}")
    public String reviewForm(@PathVariable int memberId, @RequestParam int placeId, Model model){

        model.addAttribute("memberId", memberId);
        model.addAttribute("placeId", placeId);

        return "review-Form";
    }


    /*
    리뷰 작성 API
     */

    @PostMapping("/review/{memberId}")
    public String createReview(@PathVariable int memberId, @ModelAttribute("review") ReviewDto reviewDto){

        reviewService.createReview(memberId, reviewDto);

        return "redirect:/places/" + memberId + "/placeDetail/" + reviewDto.getPlaceId();
    }


    /*
    리뷰 수정 폼
     */
    @GetMapping("/review/{memberId}/edit/{reviewId}")
    public String reviewEditForm(@PathVariable int memberId, @PathVariable int reviewId, @RequestParam int placeId, Model model){

        model.addAttribute("memberId", memberId);
        model.addAttribute("placeId", placeId);
        Review review = reviewRepository.findById(reviewId).get();
        model.addAttribute("review", review);

        return "review-Edit";
    }



    /*
    리뷰 수정 API
     */
    @PostMapping("/review/{memberId}/edit/{reviewId}")
    public String editReview(@PathVariable int memberId, @PathVariable int reviewId, @ModelAttribute("review") ReviewEditDto reviewDto, Model model){

        Review editedReview = reviewService.editReview(reviewId, reviewDto);

        int placeId = editedReview.getPlace().getPlaceId();

        model.addAttribute("review", editedReview);

        return "redirect:/places/" + memberId + "/placeDetail/" + placeId;

    }

    /*
    내가 쓴 리뷰 조회 API
     */

    @GetMapping("/myPage/{memberId}/reviews")
    public String getReview(@PathVariable int memberId, Model model){

        List<GetReviewDto> reviewList = reviewService.getReviewList(memberId);

        model.addAttribute("reviewList", reviewList);

        return "mypageReviews";
    }



    /*
    리뷰 삭제
     */
    @DeleteMapping("/review/{reviewId}")
    public String deleteReview(@PathVariable int reviewId){

        reviewService.deleteReview(reviewId);

        int memberId = reviewRepository.findById(reviewId).get().getMember().getMemberId();
        int placeId = reviewRepository.findById(reviewId).get().getPlace().getPlaceId();

        return "redirect:/places/" + memberId + "/placeDetail/" + placeId;
    }


}
