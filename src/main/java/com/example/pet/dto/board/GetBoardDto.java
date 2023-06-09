package com.example.pet.dto.board;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetBoardDto {
    private int postId;
    private String title;
    private String content;
    private int memberId;
    private String nickname;
    private LocalDateTime createdTime;
}
