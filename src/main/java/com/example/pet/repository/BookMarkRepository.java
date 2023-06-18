package com.example.pet.repository;

import com.example.pet.domain.board.Board;
import com.example.pet.domain.board.BookMark;
import com.example.pet.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookMarkRepository extends JpaRepository<BookMark, Integer> {
    @Query("SELECT b from BookMark b order by b.bookmarkId DESC")
    List<BookMark> findAllDesc();
    List<BookMark> findAllByMember_MemberId(int memberId);
    BookMark findByBoardAndMember(Board board, Member member);

    int countBookMarkByBoard(Board board);
}
