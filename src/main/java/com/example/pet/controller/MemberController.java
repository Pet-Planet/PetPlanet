package com.example.pet.controller;

import com.example.pet.config.jwt.JwtProperties;
import com.example.pet.domain.member.Member;
import com.example.pet.domain.oauth.OauthToken;
import com.example.pet.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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


    @RequestMapping(value = "/petplanet")
    public String getKakaoAuthUrl(Model model) {
        String reqUrl = KakaoAuthUrl + "/oauth/authorize?client_id=" + client_id
                + "&redirect_uri="+ RedirectUrl + "&response_type=code&prompt=login";
        model.addAttribute("reqUrl", reqUrl);
        return "login";
    }

    //access token 찾아오는
    @GetMapping("/accessToken")
    @ResponseBody
    public OauthToken getAccessToken(@RequestParam(value = "code") String code) {
        return memberService.getAccessToken(code);
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

        return "loginsuccess";
    }

    // 현재 멤버 조회
    @GetMapping("/member/{memberId}")
    @ResponseBody
    public ResponseEntity findMember(@PathVariable int memberId) {
        Member member = memberService.findMe(memberId);
        return ResponseEntity.ok().body(member);
    }


}
