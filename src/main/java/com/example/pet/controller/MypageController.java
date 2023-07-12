package com.example.pet.controller;

import com.example.pet.domain.board.BoardComment;
import com.example.pet.domain.member.Friend;
import com.example.pet.domain.member.Member;
import com.example.pet.dto.board.BoardListResponseDto;
import com.example.pet.dto.boardcomment.BoardCommentUpdateRequestDto;
import com.example.pet.dto.member.MemberResponseDto;
import com.example.pet.dto.member.MemberUpdateRequestDto;
import com.example.pet.dto.reservation.ReservationListDto;
import com.example.pet.dto.review.GetReviewDto;
import com.example.pet.repository.MemberRepository;
import com.example.pet.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage/{memberId}")
public class MypageController {

    private final MypageService mypageService;
    private final ReviewService reviewService;
    private final ReservationService reservationService;
    private final BoardCommentService boardCommentService;
    private final FriendService friendService;

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
    @GetMapping("/withdraw")
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
    public String getMyPosts(@PathVariable int memberId, Model model,
        @RequestParam(required = false, defaultValue = "0", value="sortType") int sortType) {
        List<BoardListResponseDto> boardList = mypageService.getBoardList(memberId);

        model.addAttribute("boardList", boardList);
        model.addAttribute("sortType", sortType);
        Date now = new Date();
        model.addAttribute("now", now);

        return "mypagePosts";
    }

    // 내가 쓴 댓글 조회
    @GetMapping("/comments")
    public String getMyComments(@PathVariable int memberId, Model model) {
        List<BoardComment> boardCommentList = boardCommentService.getBoardCommentsByMemberId(memberId);
        model.addAttribute("boardCommentList", boardCommentList);
        model.addAttribute("memberId", memberId);

        boardCommentList.sort(Comparator.comparing(BoardComment::getCreatedDate).reversed());

        return "mypageComments";
    }

    // 내 댓글 수정
    @GetMapping("/updateMy/{commentId}")
    public String boardCommentUpdateForm(@PathVariable int memberId, @PathVariable int commentId, Model model) {
        BoardComment updatedComment = boardCommentService.getBoardCommentById(commentId);
        model.addAttribute("comment", updatedComment);
        model.addAttribute("memberId", memberId);
        model.addAttribute("commentId", commentId);

        return "commentUpdate2";
    }

    @PostMapping("/updateMy/{commentId}")
    public String boardCommentUpdate(@PathVariable int memberId, @PathVariable int commentId, @ModelAttribute("comment") BoardCommentUpdateRequestDto commentUpdateDto, Model model) {
        boardCommentService.updateBoardComment(commentId, commentUpdateDto);
        BoardComment updatedComment = boardCommentService.getBoardCommentById(commentId);
        model.addAttribute("comment", updatedComment);
        model.addAttribute("memberId", memberId);
        model.addAttribute("commentId", commentId);

        return "redirect:/mypage/{memberId}/comments";
    }

    // 내 댓글 삭제
    @DeleteMapping("/deleteMy/{commentId}")
    public String commentDelete(@PathVariable int commentId, @PathVariable int memberId, Model model) {
        boardCommentService.deleteBoardComment(commentId);
        model.addAttribute("commentId", commentId);
        model.addAttribute("memberId", memberId);

        return "mypageComments";
    }

    // 내 북마크 조회
    @GetMapping("/bookmarks")
    public String getMyBookmarks(@PathVariable int memberId, Model model,
                                 @RequestParam(required = false, defaultValue = "0", value="sortType") int sortType) {
        List<BoardListResponseDto> boardList = mypageService.getBookmarkedBoardList(memberId);
        model.addAttribute("boardList", boardList);
        model.addAttribute("sortType", sortType);
        Date now = new Date();
        model.addAttribute("now", now);
        model.addAttribute("memberId", memberId);

        return "mypageBookmarks";
    }

    // 회원 목록 조회
    @GetMapping("/friends")
    public String getMemberList(@PathVariable int memberId, Model model, @RequestParam(required = false, defaultValue = "", value = "searchText") String searchText) {
        List<MemberResponseDto> memberList;

        if (searchText.isEmpty()) {
            memberList = mypageService.getMemberList();
        } else {
            memberList = mypageService.searchMembersByNicknameOrEmail(searchText);
        }
        model.addAttribute("memberId", memberId);
        model.addAttribute("memberList", memberList);
        model.addAttribute("searchText", searchText);

        return "mypageFriends";
    }
}
