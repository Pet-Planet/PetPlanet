package com.example.pet.dto.boardcomment;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BoardCommentUpdateRequestDto {
    private String content;

    @Builder
    public BoardCommentUpdateRequestDto(String content) {
        this.content = content;
    }
}
