package com.example.pet.controller;

import com.example.pet.dto.review.GetReviewDto;
import com.example.pet.dto.review.ReviewDto;
import com.example.pet.dto.review.ReviewEditDto;
import com.example.pet.repository.ReviewRepository;
import com.example.pet.service.MemberService;
import com.example.pet.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ReviewController {


    private final ReviewService reviewService;
    private final MemberService memberService;
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

        return "redirect:/place/placeDetail/" + reviewDto.getPlaceId();
    }


    /*
    리뷰 수정 폼
     */
    @GetMapping("/review/{memberId}/edit/{reviewId}")
    public String reviewEditForm(@PathVariable int memberId, @RequestParam int placeId, Model model){

        model.addAttribute("memberId", memberId);
        model.addAttribute("placeId", placeId);

        return "review-Edit";
    }



    /*
    리뷰 수정 API
     */
    @PutMapping("/review/{memberId}/edit/{reviewId}")
    public String editReview(@PathVariable int memberId, @PathVariable int reviewId, @ModelAttribute("review") ReviewEditDto reviewDto, Model model){

        reviewService.editReview(reviewId, reviewDto);

        model.addAttribute("memberId", memberId);

        int placeId = reviewRepository.findById(reviewId).get().getPlace().getPlaceId();


        return "redirect:/place/placeDetail/" + placeId;

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

        int placeId = reviewRepository.findById(reviewId).get().getPlace().getPlaceId();

        return "redirect:/place/placeDetail/" + placeId;
    }


}
