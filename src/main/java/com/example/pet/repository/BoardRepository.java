package com.example.pet.repository;

import com.example.pet.domain.board.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {
    @Query("SELECT b FROM Board b ORDER BY b.postId DESC")
    List<Board> findAllDesc();
    public Board findByPostId(int postId);
    List<Board> findByTitle(String title);
    List<Board> findByMember_MemberId(int memberId);
    Page<Board> findByTitleContainingOrContentContaining(String title,
                                                         String content,
                                                         Pageable pageable);
    List<Board> findTop10ByOrderByCountViewDescCreatedDateDesc();
}
