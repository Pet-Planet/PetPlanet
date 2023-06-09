package com.example.pet.service;

import com.example.pet.domain.board.Board;
import com.example.pet.domain.member.Member;
import com.example.pet.dto.member.MemberBoradListDto;
import com.example.pet.dto.member.MemberResponseDto;
import com.example.pet.dto.member.MemberUpdateRequestDto;
import com.example.pet.repository.BoardRepository;
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

    // 내가 쓴 글
//    public List<MemberBoradListDto> getBoardList(int memberId) {
//        List<Board> boardList = boardRepository.findBoardByMemberId(memberId);
//
//        List<MemberBoradListDto> boradListDto = new ArrayList<>();
//        for(Board board : boardList) {
//            MemberBoradListDto requestDto = new MemberBoradListDto(
//                    board.getPostId(),
//                    board.getTitle()
//            );
//            boradListDto.add(requestDto);
//        }
//        return boradListDto;
//    }
}
