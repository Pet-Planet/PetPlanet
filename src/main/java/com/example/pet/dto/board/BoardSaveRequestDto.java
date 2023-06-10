package com.example.pet.dto.board;

import com.example.pet.domain.board.Board;
import com.example.pet.domain.member.Member;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardSaveRequestDto {
    private String title;
    private String content;
    private String category;
    private int memberId;
    private String writer;
    private Member member;

    public Board toEntity(){
        return Board.builder()
                .title(title)
                .content(content)
                .category(category)
                .writer(writer)
                .member(member)
                .build();
    }
}
