package com.example.pet.repository;

import com.example.pet.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    public Member findByKakaoEmail(String kakaoEmail);
    public Member findByMemberId(int memberId);
    public Member findByKakaoId(Long kakaoId);
    public Member findByKakaoNickname(String nickname);
    List<Member> findByNicknameContainingIgnoreCase(String searchText);
}