package com.example.pet.dto.board;

import com.example.pet.domain.board.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BoardListResponseDto {
    private int postId;
    private String title;
    private String content;
    private String category;
    private String writer;
    private LocalDateTime createdDate;
    private int countView;

    public BoardListResponseDto(Board board) {
        this.postId = board.getPostId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.category = board.getCategory();
        this.writer = board.getWriter();
        this.createdDate = board.getCreatedDate();
        this.countView = board.getCountView();
    }

    public BoardListResponseDto(Optional<Board> board) {
        this.postId = board.get().getPostId();
        this.title = board.get().getTitle();
        this.content = board.get().getContent();
        this.category = board.get().getCategory();
        this.writer = board.get().getWriter();
    }
}