package com.example.pet.domain.member;

import java.util.Map;

public class UserAdapter extends PrincipalDetails {
    private Member member;
    private Map<String, Object> attributes;

    public UserAdapter(Member member) {
        super(member);
        this.member = member;
    }
}
