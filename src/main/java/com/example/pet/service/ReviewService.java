package com.example.pet.service;

import com.example.pet.domain.member.Member;
import com.example.pet.domain.place.Place;
import com.example.pet.domain.review.Review;
import com.example.pet.dto.review.GetReviewDto;
import com.example.pet.dto.review.ReviewDto;
import com.example.pet.dto.review.ReviewEditDto;
import com.example.pet.repository.MemberRepository;
import com.example.pet.repository.PlaceRepository;
import com.example.pet.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {


    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final PlaceRepository placeRepository;

    /*
    리뷰 작성
     */
    public Review createReview(ReviewDto reviewDto){

        Optional<Member> member = memberRepository.findById(reviewDto.getMemberId());
        Optional<Place> place = placeRepository.findById(reviewDto.getPlaceId());

        Review review = reviewRepository.save(reviewDto.toEntity());

        review.setMember(member.get());
        review.setPlace(place.get());

        return review;

    }


    /*
    리뷰 수정
     */

    public Review editReview(int reviewId, ReviewEditDto reviewEditDto){

        Optional<Review> optionalReview = reviewRepository.findById(reviewId);

        Review review = optionalReview.get();

        review.editReview(reviewEditDto.getRating(), reviewEditDto.getContent());

        return reviewRepository.save(review);

    }


    /*
    내가 쓴 리뷰 리스트 불러오기
     */

    @Transactional(readOnly = true)
    public List<GetReviewDto> getReviewList(int memberId){

        List<Review> reviewList = reviewRepository.findByMember_MemberId(memberId);

        List<GetReviewDto> reviewDtoList = new ArrayList<>();
        for(Review review : reviewList) {
            GetReviewDto reviewDto = new GetReviewDto(
                    review.getMember().getMemberId(), review.getPlace().getPlaceTitle(), review.getMember().getNickname(),
                    review.getRating(), review.getContent(), review.getCreatedDate()
            );
            reviewDtoList.add(reviewDto);
        }
        return reviewDtoList;
    }

    /*
    리뷰 삭제
     */

    public int deleteReview(int reviewId){

        reviewRepository.deleteById(reviewId);

        return reviewId;
    }


}
