package com.example.pet.repository;

import com.example.pet.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    public Member findByKakaoEmail(String kakaoEmail);
    public Member findByMemberId(Long memberId);
}