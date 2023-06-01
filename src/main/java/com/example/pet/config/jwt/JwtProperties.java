package com.example.pet.config.jwt;

// 인터페이스로 설정
public interface JwtProperties {
    // JWT의 Signature를 해싱할 때 사용되는 비밀 키
    String SECRET = "petplanet";

    // 토큰 만료 기간. 초 단위로 계산. <refresh_token을 사용하지않는다면 설정할 것!>
    int EXPIRATION_TIME = 864000000;

    // 토큰 앞에 붙는 정해진 형식. Bearer 뒤에 한 칸 공백을 넣어줘야 함
    String TOKEN_PREFIX = "Bearer ";

    // 헤더의 Authorization 이라는 항목에 토큰을 넣어줄 것
    String HEADER_STRING = "Authorization";
}
