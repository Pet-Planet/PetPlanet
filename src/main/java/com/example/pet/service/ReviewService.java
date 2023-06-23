package com.example.pet.service;

import com.example.pet.domain.member.Member;
import com.example.pet.domain.place.Place;
import com.example.pet.domain.reservation.Reservation;
import com.example.pet.domain.review.Review;
import com.example.pet.dto.review.GetReviewDto;
import com.example.pet.dto.review.ReviewDto;
import com.example.pet.dto.review.ReviewEditDto;
import com.example.pet.repository.MemberRepository;
import com.example.pet.repository.PlaceRepository;
import com.example.pet.repository.ReservationRepository;
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
    private final ReservationRepository reservationRepository;

    /*
    리뷰 작성
     */
    public Review createReview(int memberId, ReviewDto reviewDto) {
        Optional<Member> member = memberRepository.findById(memberId);
        Optional<Place> place = placeRepository.findById(reviewDto.getPlaceId());

        if (member.isPresent() && place.isPresent()) {
            // 로그인한 멤버의 ID로 예약 내역 조회
            Optional<Reservation> reservation = reservationRepository.findByMember_MemberIdAndPlace_PlaceId(memberId, reviewDto.getPlaceId());

            if (reservation.isPresent()) {

                // 이미 리뷰를 작성한 경우 에러 처리
                if (reviewRepository.existsByMember_MemberIdAndPlace_PlaceId(memberId, reviewDto.getPlaceId())) {
                    throw new IllegalStateException("이미 리뷰를 작성한 사용자입니다.");
                }

                // 예약 내역이 있는 경우 리뷰 작성
                reviewDto.setMemberId(memberId);
                Review review = reviewRepository.save(reviewDto.toEntity());

                review.setMember(member.get());
                review.setPlace(place.get());

                return review;
            } else {
                // 예약 내역이 없는 경우 리뷰 작성 제한
                throw new IllegalStateException("예약 내역이 없어 리뷰를 작성할 수 없습니다.");
            }
        } else {
            // 멤버 또는 장소 정보를 찾을 수 없는 경우 예외 처리
            throw new IllegalArgumentException("멤버 또는 장소 정보를 찾을 수 없습니다.");
        }
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

        List<Review> reviewList = reviewRepository.findByMember_MemberIdOrderByCreatedDateDesc(memberId);

        List<GetReviewDto> reviewDtoList = new ArrayList<>();

        for(Review review : reviewList) {
            GetReviewDto reviewDto = new GetReviewDto(
                    review.getId(), review.getPlace().getPlaceId(), review.getMember().getMemberId(), review.getPlace().getPlaceTitle(), review.getMember().getNickname(),
                    review.getRating(), review.getContent(), review.getLastModifiedDate()
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
