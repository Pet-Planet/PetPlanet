package com.example.pet.service;

import com.example.pet.domain.member.Member;
import com.example.pet.dto.member.MemberSignUpDto;
import com.example.pet.dto.member.MemberUpdateRequestDto;
import com.example.pet.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class LoginService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    public int signUp(MemberSignUpDto dto) throws Exception {
        if (memberRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new Exception("이미 존재하는 이메일입니ㅅ다.");
        }
        if(!dto.getPassword().equals(dto.getCheckedPassword())) {
            throw new Exception("비밀번호가 일치하지 않습니다.");
        }
        Member member = memberRepository.save(dto.toEntity());
        member.encodePassword(passwordEncoder);

        //member.addUserAuthority();
        return member.getMemberId();
    }

    // 정보수정
    // mypage에 있음
    // 회원탈퇴
    public int updatePassword(String checkPassword, String tobePassword) throws Exception {
        Member member = memberRepository.findByNickname(SecurityUtil.getLoginUsername())
                .orElseThrow(()->new Exception("회원이 존재하지 않습니다."));
        if(!member.matchPassword(passwordEncoder, checkPassword)) {
            throw new Exception("비밀번호가 일치하지 않습니다.");
        }
        member.updatePassword(passwordEncoder, tobePassword);
        return member.getMemberId();
    }
    // 정보조회
    public void withdraw(String checkPassword) throws  Exception {
        Member member = memberRepository.findByNickname(SecurityUtil.getLoginUsername())
                .orElseThrow(()->new Exception("회원이 존재하지 않습니다."));
        if(!member.matchPassword(passwordEncoder, checkPassword)) {
            throw new Exception("비밀번호가 일치하지 않습니다.");
        }
        memberRepository.delete(member);
    }
}
