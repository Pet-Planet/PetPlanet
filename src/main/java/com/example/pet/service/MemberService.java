package com.example.pet.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.pet.config.jwt.JwtProperties;
import com.example.pet.config.jwt.JwtProvider;
import com.example.pet.domain.Role;
import com.example.pet.domain.member.Member;
import com.example.pet.domain.oauth.KakaoProfile;
import com.example.pet.domain.oauth.OauthToken;
import com.example.pet.dto.member.TokenDto;
import com.example.pet.repository.MemberRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.Date;

@Transactional
@Service
@Slf4j
public class MemberService {

    @Value("${kakao.clientId}")
    String client_id;
    @Value("${kakao.secret}")
    String client_secret;
    @Value("http://localhost:8088/oauth/token")
    private String RedirectUrl;
    @Autowired
    MemberRepository memberRepository;
    JwtProvider jwtProvider;

    public OauthToken getAccessToken(String code) {
        //(2)RestTemplate 객체를 만든다. 통신에 유용한 클래스이다. 클래스에 대해 자세히 알고싶다면 구글에 서치!
        RestTemplate rt = new RestTemplate();

        //(3)HttpHeader 객체를 생성한다. 헤더에 들어가야하는 정보는 공식 문서를 잘 찾아보자.
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        //(4)HttpBody 객체를 생성한다. 바디에도 참 많은..🤤 파라미터가 요구된다. 마찬가지로 공식 문서를 찾아보면 나와있다. Required 항목에 필수라고 체크된 것만 넣으면 된다. 만약 앱을 등록할 때 시크릿 키를 만들었다면 반드시 넣어야 한다.
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", client_id);
        params.add("redirect_uri", RedirectUrl);
        params.add("code", code);
        params.add("client_secret", client_secret);

        //(5)HttpEntity 객체를 생성한다. 앞서 만든 HttpHeader 와 HttpBody 정보를 하나의 객체에 담기 위해서이다.
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
                new HttpEntity<>(params, headers);

        //(6)ResponseEntity 객체를 String 형만 받도록 생성해준다. 이유는 응답받는 값이 Json 형식이기 때문이다.
        ResponseEntity<String> accessTokenResponse = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );
        log.info("응답 내용 : " + accessTokenResponse);
        log.info("응답 내용 : " + accessTokenResponse.getBody());
        //(7)String으로 받은 Json 형식의 데이터를 ObjectMapper 라는 클래스를 사용해 객체로 변환해줄 것이다. 그러기 위해서는 해당 Json 형식과 맞는 OauthToken 이라는 클래스를 만들어 줘야한다(👇아래 참고).
        //.readValue(Json 데이터, 변환할 클래스) 메소드를 이용해 바디값을 읽어온다.
        ObjectMapper objectMapper = new ObjectMapper();
        OauthToken oauthToken = null;
        try {
            oauthToken = objectMapper.readValue(accessTokenResponse.getBody(), OauthToken.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return oauthToken; //(8)Json 데이터가 OauthToken 객체에 잘 담기면 리턴해준다.
    }

    public TokenDto saveUserAndGetToken(String token) {
        //(1) findProfile()이라는 메소드를 이용해 엑세스 토큰으로 카카오 서버에서 사용자 정보를 가져온다
        // saveUser() 메소드 아래에 구현한다
        KakaoProfile profile = findProfile(token);

        Member member = memberRepository.findByKakaoEmail(profile.getKakao_account().getEmail());
        if(member == null) {
            member = Member.builder()
                    .kakaoId(profile.getId())
                    .kakaoProfileImg(profile.getKakao_account().getProfile().getProfile_image_url())
                    .kakaoNickname(profile.getKakao_account().getProfile().getNickname())
                    .kakaoEmail(profile.getKakao_account().getEmail())
                    .role(Role.USER)
                    .build();

            memberRepository.save(member);
        }
        Authentication authentication = new UsernamePasswordAuthenticationToken(member, null, member.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return createToken(member);
    }

    public TokenDto createToken(Member member) {
        // (2-2)
//        String jwtToken = JWT.create()
//
//                // (2-3)Payload에 들어갈 등록된 클레임을 설정한다
//                // withSubject : jwt의 이름을 정한다
//                // withExpiresAt : jwt 만료시간을 지정한다
//                .withSubject(member.getKakaoNickname())
//                .withExpiresAt(new Date(System.currentTimeMillis()+ JwtProperties.EXPIRATION_TIME))
//
//                //(2-4)Payload 에 들어갈 개인 클레임 을 설정한다.
//                //.withClaim(이름, 내용) 형태로 작성한다. 사용자를 식별할 수 있는 값과, 따로 추가하고 싶은 값을 자유롭게 넣는다.
//                .withClaim("id", member.getMemberId())
//                .withClaim("nickname", member.getKakaoNickname())
//
//                //(2-5)Signature 를 설정한다. 위와 같이 알고리즘을 명시하고 앞서 만든 JwtProperties 의 비밀 키 필드를 불러와 넣어준다.
//                .sign(Algorithm.HMAC512(JwtProperties.SECRET));
        TokenDto token = jwtProvider.createToken(member.getKakaoNickname(), member.getRole());
        // (2-6) 만들어진 JWT 를 반환한다
        return token;
    }

    public String createRefreshToken(Member member, String AccessToken) {
        return JWT.create()
                .withSubject(member.getKakaoNickname())
                .withClaim("AccessToken", AccessToken)
                .withClaim("nickname", member.getKakaoNickname())
                .sign(Algorithm.HMAC512(JwtProperties.SECRET));
    }

    public Member findMe(int memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                ()->new IllegalArgumentException("해당 회원이 존재하지 않습니다.")
        );

        return member;
    }

    public Member getMember(HttpServletRequest request) {
        int memberId = (int) request.getAttribute("memberId");

        Member member = memberRepository.findById(memberId).orElseThrow(
                ()->new IllegalArgumentException("해당 회원이 존재하지 않습니다.")
        );

        return member;
    }

    // (1-1)응답 받은 Json 데이터와 정확히 일치하는 KakaoProfile 클래스를 만든다
    public KakaoProfile findProfile(String token) {
        //(1-2)통신에 필요한 RestTemplate 객체를 만든다. 이후 이어질 부분은 이전 포스팅에서 설명했기 때문에 간단히 짚고 넘어가겠다.
        RestTemplate rt = new RestTemplate();

        //(1-3)HttpHeader 객체를 생성한다.
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token); //(1-4)헤더에는 발급받은 엑세스 토큰을 넣어 요청해야한다. 카카오 공식 문서를 참고하자.
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        //(1-5)HttpHeader 와 HttpBody 정보를 하나의 객체에 담아준다.
        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest =
                new HttpEntity<>(headers);

        //(1-6)해당 주소로 Http 요청을 보내 String 변수에 응답을 받는다.
        // Http 요청 (POST 방식) 후, response 변수에 응답을 받음
        ResponseEntity<String> kakaoProfileResponse = rt.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.GET,
                kakaoProfileRequest,
                String.class
        );

        //(1-7)Json 응답을 KakaoProfile 객체로 변환해 리턴해준다.
        ObjectMapper objectMapper = new ObjectMapper();
        KakaoProfile kakaoProfile = null;
        try {
            kakaoProfile = objectMapper.readValue(kakaoProfileResponse.getBody(), KakaoProfile.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return kakaoProfile;
    }
}
