package com.example.pet.dto.bookmark;

import com.example.pet.domain.board.BookMark;
import com.example.pet.dto.board.BoardDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookMarkDto {
    private int memberId;
    private int postId;
    private BoardDto board;

    public BookMarkDto(BookMark bookMark) {
        this.postId = bookMark.getBookmarkId();
        this.memberId = bookMark.getBookmarkId();
    }
}
