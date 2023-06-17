//package com.example.pet.config.jwt;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.example.pet.repository.MemberRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Slf4j
//public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
//
//    private MemberRepository memberRepository;
//
//    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, MemberRepository memberRepository) {
//        super(authenticationManager);
//        this.memberRepository = memberRepository;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
//        // 요청 헤더의 Authorization 항목 값을 가져와 jwtHeader 변수에 담음.
//        String jwtHeader = ((HttpServletRequest)request).getHeader(JwtProperties.HEADER_STRING);
//
//        if(jwtHeader == null || !jwtHeader.startsWith(JwtProperties.TOKEN_PREFIX)){
//            chain.doFilter(request, response);
//            return;
//        }
//
//        String token = jwtHeader.replace(JwtProperties.TOKEN_PREFIX, "");
//
//        int memberId = 0;
//        PrincipalDetails
//    }
//}
