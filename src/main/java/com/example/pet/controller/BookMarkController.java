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
    @PostMapping("/bookmark/create")
    @ResponseBody
    public String create(@RequestBody BookMarkDto bookMarkDto) {
        log.info("북마크 생성");
        bookMarkService.create(bookMarkDto.getMemberId(), bookMarkDto.getPostId());
        return "성공";
    }
    
    // 북마크 취소하기 => 자유게시판 해당 글에서 북마크 버튼으로
    @DeleteMapping("/bookmark/delete")
    public String delete(@RequestBody BookMarkDto bookMarkDto) {
        bookMarkService.delete(bookMarkDto.getMemberId(), bookMarkDto.getPostId());
        return "북마크 취소";
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
