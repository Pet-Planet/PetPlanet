package com.example.pet.service;

import com.example.pet.domain.member.Member;
import com.example.pet.dto.member.MemberSignUpDto;
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

    public int signUp(MemberSignUpDto dto) throws Exception {
        if (memberRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new Exception("이미 존재하는 이메일이비다.");
        }
        if(!dto.getPassword().equals(dto.getCheckedPassword())) {
            throw new Exception("비밀번호가 일치하지 않습니다.");
        }
        Member member = memberRepository.save(dto.toEntity());
        member.encodePassword(passwordEncoder);

        //member.addUserAuthority();
        return member.getMemberId();
    }
}
