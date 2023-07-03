package com.example.pet.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.pet.domain.member.Member;
import com.example.pet.domain.member.PrincipalDetails;
import com.example.pet.repository.MemberRepository;
import com.example.pet.service.MemberService;
import com.example.pet.service.PrincipalDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
// Bean을 따로 등록하지 않아도 사용가능
@Component
public class JwtRequestFilter extends BasicAuthenticationFilter {

    private MemberRepository memberRepository;
    private PrincipalDetailsService principalDetailsService;

    public JwtRequestFilter(AuthenticationManager authenticationManager, MemberRepository memberRepository) {
        super(authenticationManager);
        this.memberRepository = memberRepository;
    }

    @Autowired
    MemberService memberService;

    // OncePerRequestFilter 메소드를 오버라이드
    // 컴파일 체크
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        log.info("1. 권한이나 인증이 필요한 요청이 전달됨");
        log.info(" CHECK JWT : JwtRequestFilter.doFilterInternal");
        // 요청 헤더의 Authorization 항목 값을 가져와 jwtHeader 변수에 담음.
        String jwtHeader = ((HttpServletRequest)request).getHeader(JwtProperties.HEADER_STRING);
        log.info("jwt Header: " + jwtHeader);
        log.info("===========================");

        log.info("2. Header 확인");
        if(jwtHeader == null || !jwtHeader.startsWith(JwtProperties.TOKEN_PREFIX)){
            filterChain.doFilter(request, response);
            return;
        }
        log.info("===========================");
        log.info("3. JWT 토큰을 검증해서 정상적인 사용자인지, 권한이 맞는지 확인");
        String jwtToken = jwtHeader.replace(JwtProperties.TOKEN_PREFIX, "");
        String username = null;
        int memberId = 0;
        try {
            memberId = JWT
                    .require(Algorithm.HMAC512(JwtProperties.SECRET))
                    .build()
                    .verify(jwtToken)
                    .getClaim("id")
                    .asInt();
        } catch (TokenExpiredException e){
            e.printStackTrace();
            request.setAttribute(JwtProperties.HEADER_STRING, "토큰 만료");
        } catch (JWTVerificationException e){
            e.printStackTrace();
            request.setAttribute(JwtProperties.HEADER_STRING, "유효하지않는 토큰");
        }
        log.info("===========================");
        // 원래 하던 방식
        log.info("헤더에 username 집어 넣음");
        request.setAttribute("memberId", memberId);

        log.info("memberId : " + memberId);
        log.info(" => Authenticate Start");
        // 새로 추가한 방식
        if (memberId != 0) {
            log.info("서명이 정상적으로 됨");
            log.info("4. 서명이 검증되었음");
            log.info("Authentication 생성을 위해 username으로 회원 조회 후 PrincipalDetails 객체로 감싼다.");
            Member member = memberRepository.findByMemberId(memberId);

            // 인증은 토큰 검증시 끝. 인증을 하기 위해서가 아닌 스프링 시큐리티가 수행해주는 권한처리를 위해
            // 아래와 같이 토큰을 만들어서 Authentication 객체를 강제로 만들고 그걸 세션에 저장한다
            PrincipalDetails principalDetails = new PrincipalDetails(member);
            log.info("===========================");

            log.info("5. Authentication 객체 생성");
            Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails,
                    null, // 패스워드는 없으니까 null 처리
                    principalDetails.getAuthorities());
            log.info("===========================");

            log.info("6. 시큐리티 세션에 접근하여 Authentication 객체 저장");
            // 강제로 시큐리티의 세션에 접근하여 값 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.info("===========================");
        }


        filterChain.doFilter(request, response);
    }

}
