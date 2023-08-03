package com.example.pet.repository;

import com.example.pet.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findByEmail(String email);
    Optional<Member> findByNickname(String nickname);
    Optional<Member> findByRefreshToken(String refreshToken);
    boolean existsByEmail(String email);
    public Member findByKakaoEmail(String kakaoEmail);
    public Member findByMemberId(int memberId);
    public Member findByKakaoId(Long kakaoId);
    public Member findByKakaoNickname(String nickname);
    @Query("SELECT m FROM Member m WHERE m.nickname LIKE %:searchText% OR m.kakaoEmail LIKE %:searchText%")
    List<Member> findByNicknameOrEmailContainingIgnoreCase(@Param("searchText") String searchText);
}