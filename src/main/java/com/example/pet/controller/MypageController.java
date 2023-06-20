package com.example.pet.controller;

import com.example.pet.domain.member.Member;
import com.example.pet.dto.board.BoardListResponseDto;
import com.example.pet.dto.bookmark.BookMarkDto;
import com.example.pet.dto.member.MemberResponseDto;
import com.example.pet.dto.member.MemberUpdateRequestDto;
import com.example.pet.dto.place.PlaceDetailDto;
import com.example.pet.dto.reservation.ReservationListDto;
import com.example.pet.dto.review.GetReviewDto;
import com.example.pet.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage/{memberId}")
public class MypageController {

    private final MypageService mypageService;
    private final ReviewService reviewService;
    private final ReservationService reservationService;

    //마이페이지
    @GetMapping("")
    public String findMe(@PathVariable int memberId, Model model) {
        MemberResponseDto memberResponseDto = mypageService.findMe(memberId);
        model.addAttribute("member", memberResponseDto);

        return "mypage";
    }

    //회원 수정
    @GetMapping("/edit")
    public String showEditForm(@PathVariable int memberId, Model model) {
        MemberResponseDto memberResponseDto = mypageService.findMe(memberId);
        model.addAttribute("member", memberResponseDto);
        model.addAttribute("memberId", memberId);   //중요

        return "mypageEdit";
    }

    @PostMapping("/edit")
    public String memberUpdate(@PathVariable int memberId, @ModelAttribute("member") MemberUpdateRequestDto memberUpdateDto, Model model) {
        mypageService.memberUpdate(memberId, memberUpdateDto);
        MemberResponseDto updatedMember = mypageService.findMe(memberId);
        model.addAttribute("member", updatedMember);

        return "mypage";
    }

    // 회원 탈퇴
    @PostMapping("/withdraw")
    public String withdrawMember(@PathVariable int memberId) {
        mypageService.withdrawMember(memberId);

        return "withdrawn";
    }

    // 내 예약 조회
    @GetMapping("/reservations")
    public String getMyReservation(@PathVariable int memberId, Model model){
        List<ReservationListDto> reservationList = reservationService.getMyReservation(memberId);
        model.addAttribute("reservationList", reservationList);

        return "mypageReservations";
    }

    // 내 리뷰 조회
    @GetMapping("/reviews")
    public String getMyReview(@PathVariable int memberId, Model model) {
        List<GetReviewDto> reviewList = reviewService.getReviewList(memberId);
        model.addAttribute("reviewList", reviewList);

        return "mypageReviews";
    }

    // 내가 쓴 글 조회
    @GetMapping("/posts")
    public String getMyPosts(@PathVariable int memberId, Model model) {
        List<BoardListResponseDto> boardList = mypageService.getBoardList(memberId);
        model.addAttribute("boardList", boardList);

        return "mypagePosts";
    }

    // 내 북마크 조회
    @GetMapping("/bookmarks")
    public String getMyBookmarks(@PathVariable int memberId, Model model) {
        List<BoardListResponseDto> bookmarkList = mypageService.getBookmarkedBoardList(memberId);
        model.addAttribute("bookmarkList", bookmarkList);
        model.addAttribute("memberId", memberId);

        return "mypageBookmarks";
    }
}
