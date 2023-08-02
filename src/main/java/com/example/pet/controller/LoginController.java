package com.example.pet.controller;

import com.example.pet.dto.member.MemberSignUpDto;
import com.example.pet.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/member")
@Controller
public class LoginController {
    private final LoginService loginService;
    
    // 회원가입
    @PostMapping("/join")
    @ResponseStatus(HttpStatus.OK)
    public int join(@Valid @RequestBody MemberSignUpDto dto) throws Exception {
        return loginService.signUp(dto);
    }
}
