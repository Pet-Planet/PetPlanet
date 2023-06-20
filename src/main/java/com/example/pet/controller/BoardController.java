package com.example.pet.controller;

import com.example.pet.domain.board.Board;
import com.example.pet.domain.member.Member;
import com.example.pet.dto.board.*;
import com.example.pet.repository.BoardRepository;
import com.example.pet.service.BoardService;
import com.example.pet.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
@RequestMapping("/board/{memberId}")
public class BoardController {

    private final BoardService boardService;
    private final MemberService memberService;
    private final BoardRepository boardRepository;


    // 전체 글 조회
    @GetMapping("")
    public String getAllBoard(@PathVariable int memberId, Model model,
                              @RequestParam(required = false, defaultValue = "0", value = "page") int page,
                              @RequestParam(required = false, defaultValue = "") String searchText) { //검색
//        List<BoardListResponseDto> boardList = boardService.findAllBoard();
        Page<Board> boardList = boardService.findBoardPage(searchText, searchText,page);

        //int startPage = Math.max(1, boardList.getPageable().getPageNumber() -1);
        //int endPage = Math.min(boardList.getTotalPages(), boardList.getPageable().getPageNumber()+3);
        int totalPage = boardList.getTotalPages();
        model.addAttribute("boardList", boardList);
        //model.addAttribute("startPage", startPage);
        //model.addAttribute("endPage", endPage);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("searchText", searchText);
        Date now = new Date();
        model.addAttribute("now", now);
        return "board";
    }

    // 게시글 하나 조회
    @GetMapping("/post/{postId}")
    public String getOneBoard(@PathVariable int postId, @PathVariable int memberId, Model model) {
        BoardResponseDto dto = boardService.findOneBoard(postId);
        int countView = dto.getCountView() + 1;

        Board board = Board.builder()
                .countView(countView)
                .build();

        boardService.updateView(postId, new BoardDto(board));

        //BoardResponseDto responseDto = new BoardResponseDto(board);
        model.addAttribute("board", dto);
        model.addAttribute("memberId", memberId);   //중요

        return "boardOne";
    }

    // 게시판 글 등록 폼
    @GetMapping("/post")
    public String write(@PathVariable int memberId, Model model){
        model.addAttribute("memberId", memberId);

        return "board-form";
    }
    @PostMapping("/post")
    public String boardSave(@PathVariable int memberId, @ModelAttribute("board") BoardDto boardDto) {
        Member member = memberService.findMe(memberId);
        Board board = boardService.boardSave(member, boardDto);
        int postId = board.getPostId();

        return "redirect:/board/{memberId}/post/" + postId;
    }

    // 게시판 글 수정
    @GetMapping("/update/{postId}")
    public String boardUpdateForm(@PathVariable int memberId, @PathVariable int postId, Model model) {
        BoardResponseDto updatedBoard = boardService.findOneBoard(postId);
        model.addAttribute("board", updatedBoard);
        model.addAttribute("memberId", memberId);   //중요

        return "board-update";
    }
    @PostMapping("/update/{postId}")
    public String boardUpdate(@PathVariable int memberId, @PathVariable int postId, @ModelAttribute("board") BoardUpdateRequestDto boardUpdateDto, Model model) {
        boardService.boardUpdate(postId, boardUpdateDto);
        BoardResponseDto updatedBoard = boardService.findOneBoard(postId);
        model.addAttribute("board", updatedBoard);
        model.addAttribute("memberId", memberId);   //중요

        return "boardOne";
    }

    // 게시판 글 삭제
    @DeleteMapping("/delete/{postId}")
    public String boardDelete(@PathVariable int memberId, @PathVariable int postId) {
        BoardResponseDto board = boardService.findOneBoard(postId);
        boardService.boardDelete(postId);

        return "board";
    }

    // 제목으로 검색하기
    @GetMapping("/findTitle")
    public List<BoardListResponseDto> getBoardByTitle(@RequestParam String searchText) {
        //String str = "제목";
        log.info("=> 검색 시작");
        return boardService.getBoardByTitle(searchText);
    }

}
