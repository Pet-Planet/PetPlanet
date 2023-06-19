package com.example.pet.controller;

import com.example.pet.domain.board.BookMark;
import com.example.pet.dto.bookmark.BookMarkDto;
import com.example.pet.service.BookMarkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BookMarkController {
    private final BookMarkService bookMarkService;

    // 북마크 하기
    @PostMapping("/board/{memberId}/post/{postId}/bookmark")
    @ResponseBody
    public String create(@PathVariable int memberId, @PathVariable int postId) {
        log.info("북마크 생성");
        bookMarkService.create(memberId, postId);
        return "성공";
    }
    
    // 북마크 취소하기 => 자유게시판 해당 글에서 북마크 버튼으로
    @DeleteMapping("/board/{memberId}/post/{postId}/bookmark")
    public String delete(@PathVariable int memberId, @PathVariable int postId) {
        bookMarkService.delete(memberId, postId);
        return "북마크 취소";
    }
    // 내가 한 북마크 글 모두 보기
    @GetMapping("/mypage/{memberId}/bookmarks")
    @ResponseBody
    public ResponseEntity getAllBookMark(@PathVariable int memberId) {
        List<BookMarkDto> bookMarkList = bookMarkService.findAllBookMark();
        return ResponseEntity.ok().body(bookMarkList);
    }

    // 북마크 한 글인지 확인하기
    @PostMapping("/bookmark/check")
    @ResponseBody
    public int checkBookMark(@RequestBody BookMarkDto bookMarkDto) {
        int check = bookMarkService.check(bookMarkDto.getPostId(), bookMarkDto.getMemberId());
        log.info("check의 결과" + check);
        return check;
    }
}
