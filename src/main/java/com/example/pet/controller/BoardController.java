package com.example.pet.controller;

import com.example.pet.domain.board.Board;
import com.example.pet.domain.member.Member;
import com.example.pet.dto.board.BoardListResponseDto;
import com.example.pet.dto.board.BoardSaveRequestDto;
import com.example.pet.dto.board.BoardUpdateRequestDto;
import com.example.pet.service.BoardService;
import com.example.pet.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;
    private final MemberService memberService;

    //로거 안찍힘 이유 찾아야함
    final Logger logger = LoggerFactory.getLogger(this.getClass());


    // 전체 글 조회
    @GetMapping("/posts")
    public List<BoardListResponseDto> getAllBoard() {
        return boardService.findAllBoard();
    }


    // 게시판 글 등록
    @PostMapping("/post")
    public ResponseEntity boardSave(HttpServletRequest request, @RequestBody BoardSaveRequestDto saveRequestDto) {
        Member member = memberService.getMember(request);
        String writer = member.getKakaoNickname();

        saveRequestDto.setWriter(writer);
        saveRequestDto.setMember(member);
        Board board = boardService.boardSave(saveRequestDto);

        return ResponseEntity.ok().body(board);
    }

    // 게시판 글 수정
    @PutMapping("/update/{postId}")
    public Board boardUpdate(@PathVariable Long postId, @RequestBody BoardUpdateRequestDto requestDto) {
        return boardService.boardUpdate(postId, requestDto);
    }
}