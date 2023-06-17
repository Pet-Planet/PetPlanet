package com.example.pet.controller;

import com.example.pet.domain.member.Member;
import com.example.pet.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MainPageController {

    private final MemberService memberService;

    @GetMapping("/main/{memberId}")
    public String mainPgae(@PathVariable int memberId, Model model) {
        Member member = memberService.findMe(memberId);

        return "mainpage";
    }
}
