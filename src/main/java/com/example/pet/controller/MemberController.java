package com.example.pet.controller;

import com.example.pet.config.jwt.JwtProperties;
import com.example.pet.domain.member.Member;
import com.example.pet.domain.oauth.OauthToken;
import com.example.pet.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class MemberController {
    @Value("${kakao.clientId}")
    String client_id;

    @Value("${kakao.secret}")
    String client_secret;

    @Value("http://localhost:8088/oauth/token")
    private String RedirectUrl;

    @Value("https://kauth.kakao.com")
    private String KakaoAuthUrl;

    private final MemberService memberService;

    @GetMapping(value = "/login")
    public @ResponseBody String getKakaoAuthUrl(HttpServletRequest request) {
        String reqUrl = KakaoAuthUrl + "/oauth/authorize?client_id=" + client_id
                + "&redirect_uri="+ RedirectUrl + "&response_type=code";
        return reqUrl;
    }
    //프론트에서 인가코드 받아오는 url
    @GetMapping("/oauth/token")
    public ResponseEntity getLogin(@RequestParam(value = "code") String code) {

        //넘어온 인가 코드를 통해 access_token 발급
        OauthToken oauthToken = memberService.getAccessToken(code);

        //발급 받은 accessToken 으로 카카오 회원 정보 DB 저장 후 JWT 생성
        String jwtToken = memberService.saveUserAndGetToken(oauthToken.getAccess_token());

        // 응답 헤더의 Authorization 이라는 항목에 JWT 를 넣어준다
        HttpHeaders headers = new HttpHeaders();
        headers.add(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + jwtToken);

        System.out.println("headers : " + headers);

        return ResponseEntity.ok().headers(headers).body("success");
    }

    // jwt 토큰으로 유저정보 요청하기
    @GetMapping("/me")
    public ResponseEntity<Object> getCurrentUser(HttpServletRequest request) {
        Member member = memberService.getMember(request);

        System.out.println("회원 : " + member);
        return ResponseEntity.ok().body(member);
    }

}
