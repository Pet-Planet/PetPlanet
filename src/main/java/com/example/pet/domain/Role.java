package com.example.pet.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.support.incrementer.AbstractDataFieldMaxValueIncrementer;

@Getter
@RequiredArgsConstructor
public enum Role {
    ADMIN("ROLE_ADMIN", "관리자"),
    USER("ROLE_USER", "일반 사용자");

    private final String key;
    private final String value;
}
