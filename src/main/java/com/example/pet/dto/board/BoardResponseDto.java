package com.example.pet.dto.board;

import com.example.pet.domain.board.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class BoardResponseDto {
    private int postId;
    private String title;
    private String content;
    private String writer;
    private String category;
    private int memberId;
    private LocalDateTime lastModifiedDate;
    private int countView;

    public BoardResponseDto(Board board) {
        this.postId = board.getPostId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.category = board.getCategory();
        this.writer = board.getWriter();
        this.memberId = board.getMember().getMemberId();
        this.lastModifiedDate = board.getLastModifiedDate();
        this.countView = board.getCountView();
    }
}
