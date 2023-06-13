package com.example.pet.repository;

import com.example.pet.domain.board.BoardComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardCommentRepository extends JpaRepository<BoardComment, Integer> {
    @Query("select c from BoardComment c where c.board.postId = :id")
    List<BoardComment> findBoardCommentsByBoard(@Param("id") int id);

}
