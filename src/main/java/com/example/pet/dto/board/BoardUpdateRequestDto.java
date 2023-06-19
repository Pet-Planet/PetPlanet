package com.example.pet.dto.board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
public class BoardUpdateRequestDto {
    private String title;
    private String content;
    private String category;

    @Builder
    public BoardUpdateRequestDto(String title, String content, String category) {
        this.title = title;
        this.category = category;
        this.content = content;
    }
}
