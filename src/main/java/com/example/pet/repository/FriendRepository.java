package com.example.pet.repository;

import com.example.pet.domain.member.Friend;
import com.example.pet.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {
    // 친구 신청
    Friend findByFromMemberAndToMember(Member fromMember, Member toMember);

    // 보낸 친구 신청 목록
    List<Friend> findByToMemberMemberIdAndWeId(int toMemberId, int weId);

    // 받은 친구 신청 목록
    List<Friend> findByFromMemberMemberIdAndWeId(int fromMemberId, int weId);

    // 친구 신청 승인
    Friend findByFromMemberAndToMemberAndWeId(Member fromMember, Member toMember, int weId);

    // 친구 취소, 거절, 삭제
    Friend findByFromMemberMemberIdAndToMemberMemberIdAndWeId(int fromMemberId, int toMemberId, int weId);

    // 친구 목록
    @Query("SELECT f1 FROM Friend f1, Friend f2 " +
            "WHERE f1.fromMember.memberId = :memberId " +
            "AND f1.weId = 1 " +
            "AND f2.fromMember.memberId = f1.toMember.memberId " +
            "AND f2.toMember.memberId = f1.fromMember.memberId " +
            "AND f2.weId = 1")
    List<Friend> findFriendListByMemberId(@Param("memberId") int memberId);

    // 친구 아닌 회원 목록
    @Query("SELECT m FROM Member m WHERE m.memberId != :memberId AND m.memberId NOT IN " +
            "(SELECT CASE WHEN f.toMember.memberId = :memberId THEN f.fromMember.memberId ELSE f.toMember.memberId END FROM Friend f " +
            "WHERE (f.toMember.memberId = :memberId AND f.fromMember.memberId != :memberId) OR " +
            "(f.fromMember.memberId = :memberId AND f.toMember.memberId != :memberId) AND " +
            "(f.weId = 1 OR f.weId = 0))")
    List<Member> findBListNotInRelationship(@Param("memberId") int memberId);
}