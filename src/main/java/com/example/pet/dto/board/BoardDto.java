package com.example.pet.dto.board;

import com.example.pet.domain.board.Board;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {
    private String title;
    private String content;
    private String category;
    private int memberId;
    private String writer;
    private int countView;

    public Board toEntity(){
        return Board.builder()
                .title(title)
                .content(content)
                .category(category)
                .writer(writer)
                .countView(countView)
                .build();
    }
}
