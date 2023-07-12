package com.example.pet.controller;

import com.example.pet.domain.board.BoardComment;
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

    @PostMapping("/send-request")
    public String sendFriendRequest(@PathVariable("memberId") int memberId, @RequestParam("toId") int toId) {
        friendService.sendFriendRequest(memberId, toId);

        return "redirect:/mypage/" + memberId + "/friends";
    }

    @PostMapping("/approve-request")
    public String approveFriendRequest(@PathVariable("memberId") int memberId, @RequestParam("fromId") int fromId) {
        friendService.approveFriendRequest(fromId, memberId);

        return "redirect:/mypage/" + memberId + "/friends";
    }
}
