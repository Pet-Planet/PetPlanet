package com.example.pet.controller;

import com.example.pet.domain.board.Board;
import com.example.pet.dto.board.BoardListResponseDto;
import com.example.pet.dto.board.BoardSaveRequestDto;
import com.example.pet.dto.board.BoardUpdateRequestDto;
import com.example.pet.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    // 전체 글 조회
    @GetMapping("/posts")
    public List<BoardListResponseDto> getAllBoard() {
        return boardService.findAllBoard();
    }


    // 게시판 글 등록
    @PostMapping("/post")
    public Board boardSave(@RequestBody BoardSaveRequestDto saveRequestDto) {
        return boardService.boardSave(saveRequestDto);
    }

    // 게시판 글 수정
    @PutMapping("/update/{postId}")
    public Board boardUpdate(@PathVariable Long postId, @RequestBody BoardUpdateRequestDto requestDto) {
        return boardService.boardUpdate(postId, requestDto);
    }
}
