package com.example.pet.controller;

import com.example.pet.dto.member.MemberSignUpDto;
import com.example.pet.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/member")
@Controller
public class LoginController {
    private final LoginService loginService;
    
    // 회원가입
    @PostMapping("/signUp")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity signUp(@Valid @RequestBody MemberSignUpDto dto) throws Exception {
        loginService.signUp(dto);
        return ResponseEntity.ok().body("회원가입 성공");
    }

    // 비밀번호 수정
    @PutMapping("/member/password")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity updatePassword(@Valid @RequestParam String checkPassword,
                                         @RequestParam String toBePassword) throws Exception {
        loginService.updatePassword(checkPassword, toBePassword);
        
        return ResponseEntity.ok().body("비번 변경 완료");
    }

    // 회원탈퇴
    @DeleteMapping("/member")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity withdraw(String checkPassword) throws Exception {
        loginService.withdraw(checkPassword);
        return ResponseEntity.ok().body("회원 탈퇴가 완료되었습니다.");
    }
}
