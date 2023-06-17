package com.example.pet.controller;

import com.example.pet.config.jwt.JwtProperties;
import com.example.pet.domain.member.Member;
import com.example.pet.domain.member.PrincipalDetails;
import com.example.pet.domain.oauth.OauthToken;
import com.example.pet.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

//@RestController
@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    @Value("${kakao.clientId}")
    String client_id;

    @Value("${kakao.secret}")
    String client_secret;

    @Value("${kakao.redirectUrl}")
    private String RedirectUrl;

    @Value("https://kauth.kakao.com")
    private String KakaoAuthUrl;

    private final MemberService memberService;

    HttpServletRequest request = null;

    @RequestMapping(value = "/login")
    public String getKakaoAuthUrl(Model model) {
        String reqUrl = KakaoAuthUrl + "/oauth/authorize?client_id=" + client_id
                + "&redirect_uri="+ RedirectUrl + "&response_type=code";
        model.addAttribute("reqUrl", reqUrl);
        return "login";
    }
    //프론트에서 인가코드 받아오는 url
    @RequestMapping("/oauth/token")
    public String getLogin(@RequestParam(value = "code") String code, HttpServletResponse response) throws IOException {

        //넘어온 인가 코드를 통해 access_token 발급
        OauthToken oauthToken = memberService.getAccessToken(code);

        //발급 받은 accessToken 으로 카카오 회원 정보 DB 저장 후 JWT 생성
        String jwtToken = memberService.saveUserAndGetToken(oauthToken.getAccess_token());

        // 응답 헤더의 Authorization 이라는 항목에 JWT 를 넣어준다
        HttpHeaders headers = new HttpHeaders();
        headers.add(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + jwtToken);

        response.setHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + jwtToken);

        log.info("headers : " + headers);

        return "main";
    }
    // jwt 토큰으로 유저정보 요청하기
    @GetMapping("/memberinfo")
    @ResponseBody
    public int getCurrentUser(HttpServletRequest request) {
        Member member = memberService.getMember(request);
        int memberId = member.getMemberId();
        log.info("현재 회원 id : " + memberId);
        return memberId;
    }

    // 현재 멤버 조회
    @GetMapping("/member")
    public String findMember() {

        return "index";
    }


}
