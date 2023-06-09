package com.example.pet.dto.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberUpdateRequestDto {
    private String nickname;
    private String address;
    private String petType;
    private String petName;

    @Builder
    public MemberUpdateRequestDto(String nickname, String address, String petType, String petName) {
        this.nickname = nickname;
        this.address = address;
        this.petName = petName;
        this.petType = petType;
    }
}
