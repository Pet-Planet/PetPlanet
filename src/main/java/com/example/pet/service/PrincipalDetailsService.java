package com.example.pet.service;

import com.example.pet.domain.member.Member;
import com.example.pet.domain.member.PrincipalDetails;
import com.example.pet.domain.member.UserAdapter;
import com.example.pet.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@Slf4j
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("PrincipalDetailService.loadUserByUsername");
        log.info("LOGIN");
        Member member = memberRepository.findByKakaoNickname(username);
        // 시큐리티 세션에 유저 정보 저장
        if (member == null) {
            return null;
        }
        return new PrincipalDetails(member);
    }
}
