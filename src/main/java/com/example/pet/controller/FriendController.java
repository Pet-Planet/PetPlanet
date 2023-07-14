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
@RequestMapping("/mypage/{memberId}/friends")
public class FriendController {

    private final FriendService friendService;

    public FriendController(FriendService friendService) {
        this.friendService = friendService;
    }

    // 친구 신청
    @PostMapping("/send-request")
    public String sendFriendRequest(@PathVariable("memberId") int memberId, @RequestParam("toId") int toId) {
        friendService.sendFriendRequest(memberId, toId);

        return "redirect:/mypage/{memberId}/friends";
    }

    // 보낸 친구 신청 목록
    @GetMapping("/friend-sent")
    public String getSentFriendRequests(@PathVariable("memberId") int memberId, Model model) {
        List<Friend> sentRequests = friendService.getSentFriendRequests(memberId);
        model.addAttribute("sentRequests", sentRequests);

        return "friend-sent";
    }

    // 친구 신청 취소
    @PostMapping("/cancel-request")
    public String cancelFriendRequest(@PathVariable("memberId") int memberId, @RequestParam("fromId") int fromMemberId) {
        friendService.cancelFriendRequest(fromMemberId, memberId);

        return "redirect:/mypage/{memberId}/friends/friend-sent";
    }

    // 받은 친구 신청 목록
    @GetMapping("/friend-received")
    public String getReceivedFriendRequests(@PathVariable("memberId") int memberId, Model model) {
        List<Friend> receivedRequests = friendService.getReceivedFriendRequests(memberId);
        model.addAttribute("receivedRequests", receivedRequests);

        return "friend-received";
    }

    // 친구 신청 승인
    @PostMapping("/approve-request")
    public String approveFriendRequest(@PathVariable("memberId") int memberId, @RequestParam("fromId") int fromId) {
        friendService.approveFriendRequest(fromId, memberId);

        return "redirect:/mypage/{memberId}/friends/friend-received";
    }

    // 친구 신청 거절
    @PostMapping("/reject-request")
    public String rejectFriendRequest(@PathVariable("memberId") int memberId, @RequestParam("toId") int toMemberId) {
        friendService.rejectFriendRequest(toMemberId, memberId);

        return "redirect:/mypage/{memberId}/friends/friend-received";
    }

    // 친구 목록
    @GetMapping("/friendList")
    public String friendList(@PathVariable("memberId") int memberId, Model model) {
        List<Friend> friendList = friendService.getFriendListByMemberId(memberId);
        model.addAttribute("friendList", friendList);
        return "friendList";
    }

    // 친구 삭제
    @PostMapping("/delete-request")
    public String deleteFriendRequest(@PathVariable("memberId") int memberId, @RequestParam("toId") int toMemberId) {
        friendService.deleteFriendRequest(toMemberId, memberId);

        return "redirect:/mypage/{memberId}/friends/friendList";
    }
}
