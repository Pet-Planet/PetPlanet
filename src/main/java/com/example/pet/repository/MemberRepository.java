package com.example.pet.repository;

import com.example.pet.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    public Member findByKakaoEmail(String kakaoEmail);
    public Member findByMemberId(int memberId);
    public Member findByNickname(String nickname);
}