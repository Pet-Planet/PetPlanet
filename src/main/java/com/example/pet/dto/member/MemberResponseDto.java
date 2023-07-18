package com.example.pet.dto.member;

import com.example.pet.domain.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class MemberResponseDto {
    private int memberId;
    private String kakaoNickname;
    private String kakaoEmail;
    private String nickname;
    private String address;
    private String petType;
    private String petName;
    private String kakaoProfileImg;
    private Long kakaoId;

    public MemberResponseDto(Member member){
        this.memberId = member.getMemberId();
        this.kakaoNickname = member.getKakaoNickname();
        this.kakaoEmail = member.getKakaoEmail();
        this.nickname = member.getNickname();
        this.address = member.getAddress();
        this.petName = member.getPetName();
        this.petType = member.getPetType();
        this.kakaoProfileImg = member.getKakaoProfileImg();
        this.kakaoId = member.getKakaoId();
    }

    private boolean friendRequested;
}
