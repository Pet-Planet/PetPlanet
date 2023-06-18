package com.example.pet.controller;

import com.example.pet.domain.board.Board;
import com.example.pet.domain.board.BoardComment;
import com.example.pet.domain.member.Member;
import com.example.pet.dto.board.BoardUpdateRequestDto;
import com.example.pet.dto.boardcomment.BoardCommentSaveDto;
import com.example.pet.dto.boardcomment.BoardCommentUpdateRequestDto;
import com.example.pet.dto.member.MemberResponseDto;
import com.example.pet.service.BoardCommentService;
import com.example.pet.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/board/post/{postId}")
@RequiredArgsConstructor
public class BoardCommentController {

    private final BoardCommentService boardCommentService;
    private final MemberService memberService;

    // 댓글 등록
    @PostMapping("/comment")
    public ResponseEntity<BoardComment> saveBoardComment(HttpServletRequest request, BoardCommentSaveDto dto) {
        Member member = memberService.getMember(request);

        BoardComment comment = boardCommentService.saveBoardComment(member, dto);

        return ResponseEntity.ok(comment);
    }

    // 게시물에 대한 댓글 리스트 조회
    @GetMapping("/comments")
    public ResponseEntity<List<BoardComment>> getBoardComments(@PathVariable int postId) {
        List<BoardComment> comments = boardCommentService.getBoardCommentsByPostId(postId);

        return ResponseEntity.ok(comments);
    }

    // 댓글 수정
    @PutMapping("/update/{commentId}")
    public ResponseEntity<BoardComment> updateBoardComment(@PathVariable int commentId, @RequestBody BoardCommentUpdateRequestDto requestDto) {
        BoardComment updatedComment = boardCommentService.updateBoardComment(commentId, requestDto);

        return ResponseEntity.ok(updatedComment);
    }


    // 댓글 삭제
    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<Void> deleteBoardComment(@PathVariable int commentId) {
        boardCommentService.deleteBoardComment(commentId);

        return ResponseEntity.noContent().build();
    }
}
