package com.example.pet.service;

import com.example.pet.domain.board.Board;
import com.example.pet.domain.member.Member;
import com.example.pet.dto.board.BoardListResponseDto;
import com.example.pet.dto.board.BoardSaveRequestDto;
import com.example.pet.dto.board.BoardUpdateRequestDto;
import com.example.pet.repository.BoardRepository;
import com.example.pet.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    // 전체 게시글 조회하기
    public List<BoardListResponseDto> findAllBoard() {
        try {
            List<Board> boardList = boardRepository.findAll();
            List<BoardListResponseDto> responseDtoList = new ArrayList<>();
            for (Board board : boardList) {
                responseDtoList.add(
                        new BoardListResponseDto(board)
                );
            }
            return responseDtoList;
        } catch (Exception e) {
        }
        return null;
    }

    // 글 조회하기

    // 게시판에 글 등록하기
    public Board boardSave(BoardSaveRequestDto requestDto) {
        Board newBoard = boardRepository.save(requestDto.toEntity());
        return newBoard;
    }

    // 글 수정하기
    public Board boardUpdate(Long id, BoardUpdateRequestDto requestDto) {
        Board board = boardRepository.findById(id).orElseThrow
                (() -> new IllegalArgumentException("해당 게시글이 없습니다. id = "+id));
        //board.update(requestDto.getTitle(), requestDto.getContent(), requestDto.getCategory());
        board.setContent(requestDto.getContent());
        board.setCategory(requestDto.getCategory());
        board.setTitle(requestDto.getTitle());
        return board;
    }
    // 글 삭제하기



}
