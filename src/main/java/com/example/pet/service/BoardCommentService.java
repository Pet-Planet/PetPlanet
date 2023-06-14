package com.example.pet.service;

import com.example.pet.domain.board.BoardComment;
import com.example.pet.dto.boardcomment.BoardCommentSaveDto;
import com.example.pet.repository.BoardCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardCommentService {

    private final BoardCommentRepository boardCommentRepository;

    public BoardComment saveBoardCommnet(BoardCommentSaveDto dto) {
        BoardComment comment = boardCommentRepository.save(dto.toEntity());
        return comment;
    }
}
