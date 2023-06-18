package com.example.pet.service;

import com.example.pet.domain.board.Board;
import com.example.pet.domain.board.BoardComment;
import com.example.pet.domain.member.Member;
import com.example.pet.dto.board.BoardUpdateRequestDto;
import com.example.pet.dto.boardcomment.BoardCommentSaveDto;
import com.example.pet.dto.boardcomment.BoardCommentUpdateRequestDto;
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

    //댓글 수정
    public BoardComment updateBoardComment(int id, BoardCommentUpdateRequestDto requestDto) {
        BoardComment boardComment = boardCommentRepository.findById(id).orElseThrow
                (() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다. id = "+id));
        boardComment.update(requestDto);

        return boardCommentRepository.save(boardComment);
    }

    //댓글 삭제
    public void deleteBoardComment(int commentId) {
        boardCommentRepository.deleteById(commentId);
    }

    // 회원이 쓴 댓글 조회
    public List<BoardComment> getBoardCommentsByMemberId(int memberId) {
        return boardCommentRepository.findByMember_MemberId(memberId);
    }
}
