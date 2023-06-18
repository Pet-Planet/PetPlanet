package com.example.pet.dto.boardcomment;

import com.example.pet.domain.board.BoardComment;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardCommentSaveDto {
    private String content;
    private String writer;
    private int postId;
    private int memberId;

    public BoardComment toEntity() {
        return BoardComment.builder()
                .content(content)
                .writer(writer)
                .build();
    }

    @Builder
    public BoardCommentSaveDto(String content) {
        this.content = content;
    }
}
