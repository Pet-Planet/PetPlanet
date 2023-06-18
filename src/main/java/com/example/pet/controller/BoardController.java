package com.example.pet.controller;

import com.example.pet.domain.board.Board;
import com.example.pet.domain.member.Member;
import com.example.pet.dto.board.*;
import com.example.pet.service.BoardService;
import com.example.pet.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;


@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;
    private final MemberService memberService;


    // 전체 글 조회
    @GetMapping("/{memberId}")
    public String getAllBoard(@PathVariable int memberId, Model model) {
        List<BoardListResponseDto> boardList = boardService.findAllBoard();
        model.addAttribute("boardList", boardList);

        Date now = new Date();
        model.addAttribute("now", now);
        return "board";
    }

    // 게시글 하나 조회
    @GetMapping("/{memberId}/post/{postId}")
    public String getOneBoard(@PathVariable int postId, @PathVariable int memberId, Model model) {
        BoardResponseDto dto = boardService.findOneBoard(postId);
        model.addAttribute("board", dto);
        return "boardOne";
    }

    // 게시판 글 등록 폼
    @GetMapping("/{memberId}/post")
    public String write(@PathVariable int memberId, Model model){
        model.addAttribute("memberId", memberId);
        return "board-form";
    }
    @PostMapping("/{memberId}/post")
    public String boardSave(@PathVariable int memberId, @ModelAttribute("board") BoardDto boardDto) {
        Member member = memberService.findMe(memberId);

        Board board = boardService.boardSave(member, boardDto);
        int postId = board.getPostId();
        return "redirect:/board/{memberId}/post/" + postId;
    }

    // 게시판 글 수정
    @PutMapping("/{memberId}/update/{postId}")
    public ResponseEntity boardUpdate(@PathVariable int memberId, @PathVariable int postId, @RequestBody BoardUpdateRequestDto requestDto) {
        Board board = boardService.boardUpdate(postId, requestDto);

        return ResponseEntity.ok().body(board);
    }

    // 게시판 글 삭제
    @DeleteMapping("/{memberId}/delete/{postId}")
    public String boardDelete(@PathVariable int memberId, @PathVariable int postId) {
        BoardResponseDto board = boardService.findOneBoard(postId);
        if (board.getMemberId() != memberId) {
            log.info("글을 삭제할 권한이 없습니다.");
            return null;
        }
        else{
            boardService.boardDelete(postId);
            return postId + " : 글이 삭제되었습니다.";
        }

    }

    // 제목으로 검색하기
    @GetMapping("/findTitle")
    public List<BoardListResponseDto> getBoardByTitle(@RequestParam String title) {
        //String str = "제목";
        return boardService.getBoardByTitle(title);
    }

}
