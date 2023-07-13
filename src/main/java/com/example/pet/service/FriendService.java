package com.example.pet.service;

import com.example.pet.domain.member.Friend;
import com.example.pet.domain.member.Member;
import com.example.pet.repository.FriendRepository;
import com.example.pet.repository.MemberRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class FriendService {

    private final FriendRepository friendRepository;
    private final MemberRepository memberRepository;

    public FriendService(FriendRepository friendRepository, MemberRepository memberRepository) {
        this.friendRepository = friendRepository;
        this.memberRepository = memberRepository;
    }

    // 친구 신청
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

    // 보낸 친구 신청 목록
    public List<Friend> getSentFriendRequests(int toMemberId) {
        return friendRepository.findByToMemberMemberIdAndWeId(toMemberId, 0);
    }

    // 친구 신청 취소
    public void cancelFriendRequest(int fromMemberId, int toMemberId) {
        Friend request1 = friendRepository.findByFromMemberMemberIdAndToMemberMemberIdAndWeId(fromMemberId, toMemberId, 0);
        Friend request2 = friendRepository.findByFromMemberMemberIdAndToMemberMemberIdAndWeId(toMemberId, fromMemberId, 1);

        if (request1 != null) {
            friendRepository.delete(request1);
        }
        if (request2 != null) {
            friendRepository.delete(request2);
        }
    }

    // 받은 친구 신청 목록
    public List<Friend> getReceivedFriendRequests(int fromMemberId) {
        return friendRepository.findByFromMemberMemberIdAndWeId(fromMemberId, 0);
    }

    // 친구 신청 승인
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

    // 친구 신청 거절
    public void rejectFriendRequest(int fromMemberId, int toMemberId) {
        Friend request1 = friendRepository.findByFromMemberMemberIdAndToMemberMemberIdAndWeId(fromMemberId, toMemberId, 1);
        Friend request2 = friendRepository.findByFromMemberMemberIdAndToMemberMemberIdAndWeId(toMemberId, fromMemberId, 0);

        if (request1 != null) {
            friendRepository.delete(request1);
        }
        if (request2 != null) {
            friendRepository.delete(request2);
        }
    }

    // 친구 목록
//    public List<Friend> getFriendList(int fromMemberId, int toMemberId) {
//        List<Friend> friendList = friendRepository.findFriendList(fromMemberId, toMemberId);
//        return friendList;
//    }


    // 친구 삭제
    public void deleteFriendRequest(int fromMemberId, int toMemberId) {
        Friend request1 = friendRepository.findByFromMemberMemberIdAndToMemberMemberIdAndWeId(fromMemberId, toMemberId, 1);
        Friend request2 = friendRepository.findByFromMemberMemberIdAndToMemberMemberIdAndWeId(toMemberId, fromMemberId, 1);

        if (request1 != null) {
            friendRepository.delete(request1);
        }
        if (request2 != null) {
            friendRepository.delete(request2);
        }
    }

    // 친구 아닌 회원 목록
    public List<Member> getBListNotInRelationship(int memberId) {
        return friendRepository.findBListNotInRelationship(memberId);
    }
}