package com.example.pet.repository;

import com.example.pet.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    public Member findByKakaoEmail(String kakaoEmail);
    public Member findByMemberId(int memberId);
    public Member findByKakaoId(Long kakaoId);
    public Member findByKakaoNickname(String nickname);
    @Query("SELECT m FROM Member m WHERE m.nickname LIKE %:searchText% OR m.kakaoEmail LIKE %:searchText%")
    List<Member> findByNicknameOrEmailContainingIgnoreCase(@Param("searchText") String searchText);
}