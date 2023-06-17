package com.example.pet.controller;

import com.example.pet.domain.board.Board;
import com.example.pet.domain.member.Member;
import com.example.pet.domain.member.PrincipalDetails;
import com.example.pet.dto.board.*;
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

    // 전체 글 조회
    @GetMapping("/posts")
    public List<BoardListResponseDto> getAllBoard() {
        return boardService.findAllBoard();
    }

    // 게시글 하나 조회
    @GetMapping("/posts/{postId}")
    public BoardResponseDto getOneBoard(@PathVariable int postId) {
        return boardService.findOneBoard(postId);
    }

    // 게시판 글 등록
    @PostMapping("/post")
    public ResponseEntity boardSave(HttpServletRequest request, @RequestBody BoardDto boardDto) {
        Member member = memberService.getMember(request);

        boardService.boardSave(member, boardDto);

        return ResponseEntity.ok().body(boardDto.getMemberId() + "success");
    }

    // 게시판 글 수정
    @PutMapping("/update/{postId}")
    public ResponseEntity boardUpdate(@PathVariable int postId, @RequestBody BoardUpdateRequestDto requestDto) {
        Board board = boardService.boardUpdate(postId, requestDto);

        return ResponseEntity.ok().body(board);
    }

    // 게시판 글 삭제
    @DeleteMapping("/delete/{postId}")
    public String boradDelete(@PathVariable int postId) {
        boardService.boardDelete(postId);
        return postId + " : 글이 삭제되었습니다.";
    }

    // 제목으로 검색하기
    @GetMapping("/findTitle")
    public List<BoardListResponseDto> getBoardByTitle(@RequestParam String title) {
        //String str = "제목";
        return boardService.getBoardByTitle(title);
    }

}
