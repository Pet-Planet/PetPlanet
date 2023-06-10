package com.example.pet.controller;

import com.example.pet.domain.member.Member;
import com.example.pet.dto.board.GetBoardDto;
import com.example.pet.dto.member.MemberResponseDto;
import com.example.pet.dto.member.MemberUpdateRequestDto;
import com.example.pet.service.BoardService;
import com.example.pet.service.MypageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/myPage")
public class MypageController {

    private final MypageService mypageService;
    private final BoardService boardService;

    // 유저 정부 추가 및 수정하기
    @PutMapping("/edit")
    public ResponseEntity memberUpdate(HttpServletRequest request, @RequestBody MemberUpdateRequestDto requestDto) {
        int id = mypageService.getMember(request).getMemberId();
        Member member = mypageService.memberUpdate(id, requestDto);

        return ResponseEntity.ok().body(member);
    }

    // 내 개인정보 보기 => 상세보기 눌러야함
    @GetMapping("")
    public MemberResponseDto findMe(HttpServletRequest request) {
        int id = mypageService.getMember(request).getMemberId();
        return mypageService.findMe(id);
    }

    // 내가 쓴 글 조회
    @GetMapping("/boards")
    public List<GetBoardDto> getBoard(HttpServletRequest request) {
        int memberId = mypageService.getMember(request).getMemberId();

        return mypageService.getBoardList(memberId);
    }

}