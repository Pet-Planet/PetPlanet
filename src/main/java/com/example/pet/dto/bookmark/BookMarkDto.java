package com.example.pet.dto.bookmark;

import com.example.pet.domain.board.BookMark;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookMarkDto {
    private int bookmarkId;
    private int memberId;
    private int postId;

    public BookMarkDto(BookMark bookMark) {
        this.postId = bookMark.getBookmarkId();
        this.memberId = bookMark.getBookmarkId();
        this.bookmarkId = bookMark.getBookmarkId();
    }
}
