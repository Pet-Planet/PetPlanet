package com.example.pet.dto.member;

import com.example.pet.domain.Role;
import com.example.pet.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
public class MemberSignUpDto {
    @NotBlank(message = "아이디를 입력해주세요")
    private String email;

    @NotBlank(message = "닉네임을 입력해주세요.")
    @Size(min=2, message = "닉네임이 너무 짧습니다.")
    private String nickname;
    @NotBlank(message = "비밀번호를 입력해주세요")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,30}$",
            message = "비밀번호는 8~30 자리이면서 1개 이상의 알파벳, 숫자, 특수문자를 포함해야합니다.")
    private String password;

    private String checkedPassword;
    private String address;
    private String petType;
    private String petName;

    @Builder
    public Member toEntity(){
        return Member.builder()
                .email(email)
                .nickname(nickname)
                .password(password)
                .address(address)
                .petName(petName)
                .petType(petType)
                .build();
    }
}
