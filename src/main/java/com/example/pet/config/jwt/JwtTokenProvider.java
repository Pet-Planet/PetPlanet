//package com.example.pet.config.jwt;
//
//import com.example.pet.config.exception.CustomException;
//import com.example.pet.domain.oauth.OauthToken;
//import com.sun.xml.messaging.saaj.packaging.mime.util.BASE64DecoderStream;
//import io.jsonwebtoken.*;
//import lombok.Data;
//import lombok.extern.slf4j.Slf4j;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Controller;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Date;
//
//@Component
//@Slf4j
//public class JwtTokenProvider {
//    private String secret_key = JwtProperties.SECRET;
//    private long expire_time = JwtProperties.EXPIRATION_TIME;
//
//    UserDetailsService userDetailsService;
//
//    // 유저 정보를 가지고 AccessToken, RefreshToken 을 생성하는 메소드
//    public OauthToken generateToken(Authentication authentication) {
//        // 적절한 설정을 통해 토큰을 생성하여 반환
//        Claims claims = Jwts.claims().setSubject(authentication.getName());
//
//        Date now = new Date();
//        Date expiresIn = new Date(now.getTime() + expire_time);
//
//        return Jwts.builder()
//                .setClaims(claims)
//                .setIssuedAt(now)
//                .setExpiration(expiresIn)
//                .signWith(SignatureAlgorithm.HS256, secret_key)
//                .compact();
//    }
//
//    public Authentication getAuthentication(String token) {
//        //토큰으로 클레임을 만들고 이를 통해 User 객체를 생성하여 Authentication 객체를 반환
//        String username = Jwts.parser().setSigningKey(secret_key).parseClaimsJws(token).getBody().getSubject();
//        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//
//        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
//    }
//
//    // http 헤더로부터 bearer 토큰을 가져옴
//    public String resolveToken(HttpServletRequest request) {
//        String bearerToken = request.getHeader("Authorization");
//        if ( bearerToken != null && bearerToken.startsWith("Bearer ")) {
//            return bearerToken.substring(7);
//        }
//        return null;
//    }
//
//    // 토큰 검증
//    public boolean validateToken(String token) {
//        try {
//            Jwts.parser().setSigningKey(secret_key).parseClaimsJws(token);
//            return true;
//        } catch (ExpiredJwtException e) {
//            logger.info("만료된 JWT 토큰입니다.");
//        } catch (UnsupportedJwtException e) {
//            logger.info("지원되지 않는 JWT 토큰입니다.");
//        } catch (IllegalArgumentException e) {
//            logger.info("JWT 토크이 잘못되었습니다.");
//        }
//        return false;
//    }
//
//}
