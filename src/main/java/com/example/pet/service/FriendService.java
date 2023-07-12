package com.example.pet.service;

import com.example.pet.domain.member.Friend;
import com.example.pet.domain.member.Member;
import com.example.pet.repository.FriendRepository;
import com.example.pet.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class FriendService {

    private final FriendRepository friendRepository;
    private final MemberRepository memberRepository;

    public FriendService(FriendRepository friendRepository, MemberRepository memberRepository) {
        this.friendRepository = friendRepository;
        this.memberRepository = memberRepository;
    }

    public void sendFriendRequest(int fromMemberId, int toMemberId) {
        Member fromMember = memberRepository.findById(fromMemberId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid member ID: " + fromMemberId));
        Member toMember = memberRepository.findById(toMemberId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid member ID: " + toMemberId));

        Friend existingRequest = friendRepository.findByFromMemberAndToMember(fromMember, toMember);

        if (existingRequest != null) {
            return;
        }

        Friend friendRequest = Friend.builder()
                .fromMember(fromMember)
                .toMember(toMember)
                .weId(1)
                .kakaoProfileImg(toMember.getKakaoProfileImg())
                .nickname(toMember.getNickname())
                .kakaoEmail(toMember.getKakaoEmail())
                .build();

        friendRepository.save(friendRequest);

        Friend reciprocalRequest = Friend.builder()
                .fromMember(toMember)
                .toMember(fromMember)
                .weId(0)
                .kakaoProfileImg(fromMember.getKakaoProfileImg())
                .nickname(fromMember.getNickname())
                .kakaoEmail(fromMember.getKakaoEmail())
                .build();

        friendRepository.save(reciprocalRequest);
    }


    public void approveFriendRequest(int fromMemberId, int toMemberId) {
        Member fromMember = memberRepository.findById(fromMemberId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid member ID: " + fromMemberId));
        Member toMember = memberRepository.findById(toMemberId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid member ID: " + toMemberId));

        Friend friendRequest = friendRepository.findByFromMemberAndToMemberAndWeId(toMember, fromMember, 0);

        if (friendRequest != null) {
            friendRequest.setWeId(1);
            friendRepository.save(friendRequest);
        }
    }
}