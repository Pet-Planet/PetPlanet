package com.example.pet.controller;

import com.example.pet.config.jwt.JwtProperties;
import com.example.pet.domain.member.CustomUserDetails;
import com.example.pet.domain.member.Member;
import com.example.pet.domain.member.UserAdapter;
import com.example.pet.domain.oauth.OauthToken;
import com.example.pet.dto.member.MemberUpdateRequestDto;
import com.example.pet.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

@RestController
@RequiredArgsConstructor
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

    @RequestMapping(value = "/login")
    public String getKakaoAuthUrl(Model model) {
        String reqUrl = KakaoAuthUrl + "/oauth/authorize?client_id=" + client_id
                + "&redirect_uri="+ RedirectUrl + "&response_type=code";
        model.addAttribute("reqUrl", reqUrl);
        return reqUrl;
    }
    //프론트에서 인가코드 받아오는 url
    @RequestMapping("/oauth/token")
    public ResponseEntity<Object> getLogin(@RequestParam(value = "code") String code) {

        //넘어온 인가 코드를 통해 access_token 발급
        OauthToken oauthToken = memberService.getAccessToken(code);

        //발급 받은 accessToken 으로 카카오 회원 정보 DB 저장 후 JWT 생성
        String jwtToken = memberService.saveUserAndGetToken(oauthToken.getAccess_token());

        // 응답 헤더의 Authorization 이라는 항목에 JWT 를 넣어준다
        HttpHeaders headers = new HttpHeaders();
        headers.add(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + jwtToken);

        System.out.println("headers : " + headers);

//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        UserDetails userDetails = (UserDetails) principal;
//        String name = userDetails.getUsername();
        return ResponseEntity.ok().headers(headers).body("success");
    }
    // jwt 토큰으로 유저정보 요청하기
    @GetMapping("/memberinfo")
    public ResponseEntity<Object> getCurrentUser(HttpServletRequest request) {
        Member member = memberService.getMember(request);

        System.out.println("회원 : " + member);
        return ResponseEntity.ok().body(member);
    }

    @GetMapping("/test/login")
    public String testLogin(@AuthenticationPrincipal CustomUserDetails oauth) {
        System.out.println("----------test login--------");
        System.out.println("oauthUser = " + oauth.getMember().getKakaoNickname());
        return "세션 정보 확인하기";
    }

}
