package com.example.pet.controller;

import com.example.pet.domain.board.Board;
import com.example.pet.domain.board.BoardComment;
import com.example.pet.domain.member.Member;
import com.example.pet.dto.board.BoardDto;
import com.example.pet.dto.board.BoardResponseDto;
import com.example.pet.dto.board.BoardUpdateRequestDto;
import com.example.pet.dto.boardcomment.BoardCommentSaveDto;
import com.example.pet.dto.boardcomment.BoardCommentUpdateRequestDto;
import com.example.pet.dto.member.MemberResponseDto;
import com.example.pet.service.BoardCommentService;
import com.example.pet.service.MemberService;
import com.example.pet.service.MypageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/board/{memberId}/post/{postId}")
@RequiredArgsConstructor
public class BoardCommentController {

    private final BoardCommentService boardCommentService;
    private final MemberService memberService;
    private final MypageService mypageService;

    //게시물에 대한 전체 댓글 조회 --> BoardController로 이동
//    @GetMapping("/comments")
//    public String getBoardComments(@PathVariable int memberId, @PathVariable int postId, Model model) {
//        List<BoardComment> comments = boardCommentService.getBoardCommentsByPostId(postId);
//        MemberResponseDto memberResponseDto = mypageService.findMe(memberId);
//        model.addAttribute("comments", comments);
//        model.addAttribute("member", memberResponseDto);
//
//        // 작성일자를 기준으로 정렬
//        comments.sort(Comparator.comparing(BoardComment::getCreatedDate));
//
//        return "commentList";
//    }

    //댓글 등록 --> BoardController로 이동
//    @GetMapping("")
//    public String commentWriteForm(@PathVariable int memberId, @PathVariable int postId, Model model){
//        MemberResponseDto memberResponseDto = mypageService.findMe(memberId);
//        model.addAttribute("member", memberResponseDto);
//        model.addAttribute("memberId", memberId);
//        model.addAttribute("postId", postId);
//
//        return "commentForm";
//    }
    @PostMapping("")
    public String commentSave(@PathVariable int memberId, @ModelAttribute("boardComment") BoardCommentSaveDto dto) {
        Member member = memberService.findMe(memberId);
        BoardComment boardComment = boardCommentService.saveBoardComment(member, dto);
        int commentId = boardComment.getId();

        return "redirect:/board/{memberId}/post/{postId}";
    }

    //댓글 수정
    @GetMapping("/update/{commentId}")
    public String boardCommentUpdateForm(@PathVariable int memberId, @PathVariable int postId, @PathVariable int commentId, Model model) {
        BoardComment updatedComment = boardCommentService.getBoardCommentById(commentId);
        model.addAttribute("comment", updatedComment);
        model.addAttribute("memberId", memberId);
        model.addAttribute("postId", postId);
        model.addAttribute("commentId", commentId);

        return "commentUpdate";
    }

    @PostMapping("/update/{commentId}")
    public String boardCommentUpdate(@PathVariable int memberId, @PathVariable int postId, @PathVariable int commentId, @ModelAttribute("comment") BoardCommentUpdateRequestDto commentUpdateDto, Model model) {
        boardCommentService.updateBoardComment(commentId, commentUpdateDto);
        BoardComment updatedComment = boardCommentService.getBoardCommentById(commentId);
        model.addAttribute("comment", updatedComment);
        model.addAttribute("memberId", memberId);
        model.addAttribute("postId", postId);
        model.addAttribute("commentId", commentId);

        return "redirect:/board/{memberId}/post/{postId}";
    }

    // 댓글 삭제
    @DeleteMapping("/delete/{commentId}")
    public String commentDelete(@PathVariable int commentId, @PathVariable int postId, @PathVariable int memberId, Model model) {
        boardCommentService.deleteBoardComment(commentId);
        model.addAttribute("commentId", commentId);
        model.addAttribute("memberId", memberId);
        model.addAttribute("postId", postId);

        return "boardOne";
    }
}
