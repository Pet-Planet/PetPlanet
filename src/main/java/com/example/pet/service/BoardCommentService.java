package com.example.pet.service;

import com.example.pet.domain.board.Board;
import com.example.pet.domain.board.BoardComment;
import com.example.pet.domain.member.Member;
import com.example.pet.dto.boardcomment.BoardCommentSaveDto;
import com.example.pet.repository.BoardCommentRepository;
import com.example.pet.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardCommentService {

    private final BoardCommentRepository boardCommentRepository;
    private final BoardRepository boardRepository;

    //댓글 작성
//    public BoardComment saveBoardComment(Member member, BoardCommentSaveDto dto) {
//        dto.setMemberId(member.getMemberId());
//
//        BoardComment comment = boardCommentRepository.save(dto.toEntity());
//        comment.setMember(member);
//
//        return comment;
//    }

    public BoardComment saveBoardComment(Member member, BoardCommentSaveDto dto) {
        dto.setMemberId(member.getMemberId());

        BoardComment comment = dto.toEntity(); // BoardComment 객체를 먼저 생성

        // 게시물(Board)을 가져와서 연결
        Board board = boardRepository.findById(dto.getPostId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid board ID"));
        comment.setBoard(board);

        // 회원(Member)을 가져와서 연결
        comment.setMember(member);

        BoardComment savedComment = boardCommentRepository.save(comment);

        return savedComment;
    }

    //댓글 조회
    public BoardComment getBoardCommentById(int commentId) {
        return boardCommentRepository.findById(commentId).orElse(null);
    }

    //글 하나에 댓글 조회
    public List<BoardComment> getBoardCommentsByPostId(int postId) {
        return boardCommentRepository.findBoardCommentsByBoard(postId);
    }

    //댓글 삭제
    public void deleteBoardComment(int commentId) {
        boardCommentRepository.deleteById(commentId);
    }

    //댓글 수정
    public BoardComment updateBoardComment(int commentId, String newContent) {
        BoardComment comment = getBoardCommentById(commentId);
        comment.setContent(newContent);

        return boardCommentRepository.save(comment);
    }

    // 회원이 쓴 댓글 조회
    public List<BoardComment> getBoardCommentsByMemberId(int memberId) {
        return boardCommentRepository.findByMember_MemberId(memberId);
    }
}
