package com.example.pet.controller;

import com.example.pet.domain.member.Member;
import com.example.pet.dto.board.BoardListResponseDto;
import com.example.pet.dto.member.MemberBoradListDto;
import com.example.pet.dto.member.MemberUpdateRequestDto;
import com.example.pet.service.BoardService;
import com.example.pet.service.MemberService;
import com.example.pet.service.MypageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/me")
public class MypageController {

    private final MypageService mypageService;
    private final BoardService boardService;
    // jwt 토큰으로 유저정보 요청하기
    @GetMapping("")
    public ResponseEntity<Object> getCurrentUser(HttpServletRequest request) {
        Member member = mypageService.getMember(request);

        System.out.println("회원 : " + member);
        return ResponseEntity.ok().body(member);
    }

    // 유저 정부 추가 및 수정하기
    @PutMapping("/edit")
    public ResponseEntity memberUpdate(HttpServletRequest request, @RequestBody MemberUpdateRequestDto requestDto) {
        int id = mypageService.getMember(request).getMemberId();
        Member member = mypageService.memberUpdate(id, requestDto);

        return ResponseEntity.ok().body(member);
    }

    // 유저가 작성한 글 모두 보기
//    @GetMapping("/posts")
//    public List<MemberBoradListDto> memberPosts(HttpServletRequest request) {
//        int id = mypageService.getMember(request).getMemberId();
//
//        return mypageService.getBoardList(id);
//    }

}
