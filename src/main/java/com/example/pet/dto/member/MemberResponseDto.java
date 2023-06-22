package com.example.pet.dto.member;

import com.example.pet.domain.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MemberResponseDto {
    private String kakaoNickname;
    private String kakaoEmail;
    private String nickname;
    private String address;
    private String petType;
    private String petName;
    private String kakaoProfileImg;
    private Long kakaoId;

    public MemberResponseDto(Member member){
        this.kakaoNickname = member.getKakaoNickname();
        this.kakaoEmail = member.getKakaoEmail();
        this.nickname = member.getNickname();
        this.address = member.getAddress();
        this.petName = member.getPetName();
        this.petType = member.getPetType();
        this.kakaoProfileImg = member.getKakaoProfileImg();
        this.kakaoId = member.getKakaoId();
    }

}
