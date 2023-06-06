package com.example.pet.dto.board;

import com.example.pet.domain.board.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class BoardResponseDto {
    private String title;
    private String content;
    private String writer;
    private String category;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    public BoardResponseDto(Board board) {
        this.title = board.getTitle();
        this.content = board.getContent();
        this.category = board.getCategory();
        this.writer = board.getWriter();
        this.createdDate = board.getCreatedDate();
        this.lastModifiedDate = board.getLastModifiedDate();
    }
}
