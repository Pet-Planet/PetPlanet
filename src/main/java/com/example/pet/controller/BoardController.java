package com.example.pet.controller;

import com.example.pet.domain.board.Board;
import com.example.pet.domain.board.BoardComment;
import com.example.pet.domain.member.Member;
import com.example.pet.dto.board.BoardDto;
import com.example.pet.dto.board.BoardListResponseDto;
import com.example.pet.dto.board.BoardResponseDto;
import com.example.pet.dto.board.BoardUpdateRequestDto;
import com.example.pet.dto.member.MemberResponseDto;
import com.example.pet.service.BoardCommentService;
import com.example.pet.service.BoardService;
import com.example.pet.service.MemberService;
import com.example.pet.service.MypageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.Date;
import java.util.List;


@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/board/{memberId}")
public class BoardController {

    private final BoardService boardService;
    private final MemberService memberService;
    private final BoardCommentService boardCommentService;
    private final MypageService mypageService;

    // 전체 글 조회
    @GetMapping("")
    public String getAllBoard(@PathVariable int memberId, Model model,
                              @RequestParam(required = false, defaultValue = "0", value = "page") int page,
                              @RequestParam(required = false, defaultValue = "", value = "searchText") String searchText,
                              @RequestParam(required = false, defaultValue = "0", value="sortType") int sortType) {

        Page<Board> boardList = boardService.findBoardPage(searchText, searchText, page, sortType);

        int totalPage = boardList.getTotalPages();
        model.addAttribute("boardList", boardList);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("searchText", searchText);
        model.addAttribute("sortType", sortType);
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

        //추가
        List<BoardComment> comments = boardCommentService.getBoardCommentsByPostId(postId);
        MemberResponseDto memberResponseDto = mypageService.findMe(memberId);
        model.addAttribute("comments", comments);
        model.addAttribute("member", memberResponseDto);
        model.addAttribute("postId", postId);

        // 작성일자를 기준으로 정렬
        comments.sort(Comparator.comparing(BoardComment::getCreatedDate));

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

        return "redirect:/board/{memberId}/post/{postId}";
    }

    // 게시판 글 삭제
    @DeleteMapping("/delete/{postId}")
    public String boardDelete(@PathVariable int memberId, @PathVariable int postId) {
        BoardResponseDto board = boardService.findOneBoard(postId);
        boardService.boardDelete(postId);
        
        log.info(" => 게시글 삭제");
        return "redirect:/board/{memberId}";
    }

}
