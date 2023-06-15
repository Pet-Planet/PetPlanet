package com.example.pet.domain.member;

import org.springframework.security.config.core.userdetails.UserDetailsMapFactoryBean;

import java.util.Map;

public class UserAdapter extends CustomUserDetails{
    private Member member;
    private Map<String, Object> attributes;

    public UserAdapter(Member member) {
        super(member);
        this.member = member;
    }
}
