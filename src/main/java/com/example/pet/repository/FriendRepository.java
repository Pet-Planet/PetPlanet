package com.example.pet.repository;

import com.example.pet.domain.member.Friend;
import com.example.pet.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {
    Friend findByFromMemberAndToMember(Member fromMember, Member toMember);
    Friend findByFromMemberAndToMemberAndWeId(Member fromMember, Member toMember, int weId);
}