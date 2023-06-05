package com.example.pet.dto.board;

import com.example.pet.domain.board.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardSaveRequestDto {
    private String title;
    private String content;
    private String category;
    @Builder
    public BoardSaveRequestDto(String title, String content, String category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }

    public Board toEntity(){
        return Board.builder()
                .title(title)
                .content(content)
                .category(category)
                .build();
    }
}
