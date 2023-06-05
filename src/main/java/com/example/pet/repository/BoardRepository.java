package com.example.pet.repository;

import com.example.pet.domain.board.Board;
import com.example.pet.dto.board.BoardListResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query("SELECT b FROM Board b ORDER BY b.postId DESC")
    List<BoardListResponseDto> findAllDesc();
}
