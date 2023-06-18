package com.example.pet.controller;

import com.example.pet.domain.member.Member;
import com.example.pet.dto.board.GetBoardDto;
import com.example.pet.dto.member.MemberResponseDto;
import com.example.pet.dto.member.MemberUpdateRequestDto;
import com.example.pet.service.BoardService;
import com.example.pet.service.MypageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MypageController {

    private final MypageService mypageService;
    private final BoardService boardService;

    // 내 개인정보 보기 => 상세보기 눌러야함

    @GetMapping("/{memberId}")
    public String findMe(@PathVariable int memberId, Model model) {
        MemberResponseDto memberResponseDto = mypageService.findMe(memberId);
        model.addAttribute("member", memberResponseDto);

        return "mypage";
    }

    @GetMapping("/{memberId}/edit")
    public String showEditForm(@PathVariable int memberId, Model model) {

        // 기존 회원 정보 가져오기
        MemberResponseDto memberResponseDto = mypageService.findMe(memberId);
        model.addAttribute("member", memberResponseDto);

        return "mypageEdit";
    }

    @PostMapping("/{memberId}/edit")
    public String memberUpdate(@PathVariable int memberId, @ModelAttribute("member") MemberUpdateRequestDto memberUpdateDto, Model model) {

        // 회원 정보 업데이트
        mypageService.memberUpdate(memberId, memberUpdateDto);

        // 수정된 회원 정보 가져오기
        MemberResponseDto updatedMember = mypageService.findMe(memberId);

        model.addAttribute("member", updatedMember);

        return "mypage";
    }
}
