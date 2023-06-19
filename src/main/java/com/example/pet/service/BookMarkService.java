package com.example.pet.service;

import com.example.pet.domain.board.Board;
import com.example.pet.domain.board.BookMark;
import com.example.pet.domain.member.Member;
import com.example.pet.dto.bookmark.BookMarkDto;
import com.example.pet.repository.BoardRepository;
import com.example.pet.repository.BookMarkRepository;
import com.example.pet.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BookMarkService {

    private final BookMarkRepository bookMarkRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    // 북마크 생성
    public void create(int memberId, int postId) {

        Member member = memberRepository.findByMemberId(memberId);
        Board board = boardRepository.findByPostId(postId);

        BookMark bookMark = BookMark.builder()
                .board(board)
                .member(member)
                .build();
        bookMarkRepository.save(bookMark);
    }

    // 북마크 취소
    public void delete(int memberId, int postId) {

        Member member = memberRepository.findByMemberId(memberId);
        Board board = boardRepository.findByPostId(postId);
        BookMark bookMark = bookMarkRepository.findByBoardAndMember(board, member);

        bookMarkRepository.delete(bookMark);
    }

    // 북마크 개수 세기
    public int count(int postId) {
        Board board = boardRepository.findByPostId(postId);

        return bookMarkRepository.countBookMarkByBoard(board);
    }
    // 북마크 한 글인지 확인하기
    public int check(int postId, int memberId) {
        Member member = memberRepository.findByMemberId(memberId);
        Board board = boardRepository.findByPostId(postId);
        BookMark bookMark = bookMarkRepository.findByBoardAndMember(board, member);
        if (bookMark == null) {
            return 0;
        }else
            return 1;
    }

    // 내가 북마크한 글 보기
    public List<BookMarkDto> findAllBookMark() {
        try {
            List<BookMark> bookMarkList = bookMarkRepository.findAllDesc();
            List<BookMarkDto> responseList = new ArrayList<>();
            for(BookMark bookMark : bookMarkList) {
                responseList.add(
                        new BookMarkDto(bookMark)
                );
            }
            return responseList;
        } catch (Exception e) {

    } return null;
    }
}
