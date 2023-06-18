package com.example.pet.controller;

import com.example.pet.dto.review.GetReviewDto;
import com.example.pet.dto.review.ReviewDto;
import com.example.pet.dto.review.ReviewEditDto;
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


    /*
    리뷰 작성 폼
     */
    @GetMapping("/review")
    public String reviewForm(@RequestParam int placeId, Model model){

        model.addAttribute("placeId", placeId);

        return "review-Form";
    }


    /*
    리뷰 작성 API
     */

    @PostMapping("/review")
    public String createReview(HttpServletRequest request, @ModelAttribute("review") ReviewDto reviewDto){

        int memberId = memberService.getMember(request).getMemberId();

        reviewService.createReview(memberId, reviewDto);

        // 추후 리뷰작성 성공시 장소 상세페이지로 리다이렉트로 수정
       return "review-success";
    }

    /*
    리뷰 수정 API
     */
    @PutMapping("/review/edit/{reviewId}")
    public String editReview(@PathVariable int reviewId, @ModelAttribute("review") ReviewEditDto reviewDto){

        reviewService.editReview(reviewId, reviewDto);

        // 추후 리뷰수정 성공시 장소 상세페이지로 리다이렉트로 수정
        return "review-success";

    }


        /*
    내가 쓴 리뷰 조회 API
     */

    @GetMapping("/myPage/reviews")
    public List<GetReviewDto> getReview(HttpServletRequest request){
        int memberId = memberService.getMember(request).getMemberId();

        return reviewService.getReviewList(memberId);
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
