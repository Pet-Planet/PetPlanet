package com.example.pet.service;

import com.example.pet.domain.board.Board;
import com.example.pet.domain.board.BookMark;
import com.example.pet.domain.member.Member;
import com.example.pet.dto.board.BoardListResponseDto;
import com.example.pet.dto.member.MemberResponseDto;
import com.example.pet.dto.member.MemberUpdateRequestDto;
import com.example.pet.repository.BoardRepository;
import com.example.pet.repository.BookMarkRepository;
import com.example.pet.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MypageService {
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final BookMarkRepository bookmarkRepository;

    public Member getMember(HttpServletRequest request) {
        int memberId = (int) request.getAttribute("memberId");

        Member member = memberRepository.findByMemberId(memberId);

        return member;
    }
    // 내 정보 보기
    public MemberResponseDto findMe(int id) {
        Member member = memberRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("해당 회원이 존재하지 않습니다.")
        );
        return new MemberResponseDto(member);
    }

    // 회원정보 수정하기
    public Member memberUpdate(int id, MemberUpdateRequestDto requestDto) {
        Member member = memberRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 회원이 존재하지 앟습니다. id = " + id));
        // 존재하는 회원인지 확인 후 수정
        member.memberUpdate(requestDto);

        return memberRepository.save(member);
    }

    // 회원 탈퇴
//    public void withdrawMember(int id) {
//        Member member = memberRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
//
//        memberRepository.delete(member);
//    }
    // 회원 탈퇴 버전 2
    public void withdrawMember(int id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
        member.setStatus(0);
        memberRepository.save(member);
    }

    // 내가 쓴 글 조회
    public List<BoardListResponseDto> getBoardList(int memberId) {
        List<Board> boardList = boardRepository.findByMember_MemberId(memberId);

        List<BoardListResponseDto> boardDtoList = new ArrayList<>();
        for(Board board : boardList) {
            boardDtoList.add(
                    new BoardListResponseDto(board)
            );
        }
        return boardDtoList;
    }

    public List<BoardListResponseDto> getBookmarkedBoardList(int memberId) {
        List<BookMark> bookmarkList = bookmarkRepository.findAllByMember_MemberId(memberId);
        List<BoardListResponseDto> boardDtoList = new ArrayList<>();

        for (BookMark bookmark : bookmarkList) {
            boardDtoList.add(new BoardListResponseDto(bookmark.getBoard()));
        }

        return boardDtoList;
    }

    //회원 목록 조회
    public List<MemberResponseDto> getMemberList() {
        List<Member> members = memberRepository.findAll();
        List<MemberResponseDto> memberResponseDtoList = new ArrayList<>();

        for (Member member : members) {
            MemberResponseDto memberResponseDto = new MemberResponseDto(member);
            memberResponseDto.setNickname(member.getNickname());
            memberResponseDtoList.add(memberResponseDto);
        }

        return memberResponseDtoList;
    }

    //회원 검색
    public List<MemberResponseDto> searchMembersByNicknameOrEmail(String searchText) {
        List<Member> members = memberRepository.findByNicknameOrEmailContainingIgnoreCase(searchText);
        List<MemberResponseDto> memberResponseDtoList = new ArrayList<>();

        for (Member member : members) {
            MemberResponseDto memberResponseDto = new MemberResponseDto(member);
            memberResponseDto.setNickname(member.getNickname());
            memberResponseDto.setKakaoEmail(member.getKakaoEmail());
            memberResponseDtoList.add(memberResponseDto);
        }

        return memberResponseDtoList;
    }
}
