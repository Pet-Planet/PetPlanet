package com.example.pet.domain.member;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Data
public class PrincipalDetails implements UserDetails {
    private Member member;

    public PrincipalDetails(Member member) {
        this.member = member;
    }

    // 해당 유저의 권한을 리턴한다
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(()->{ return member.getRole().toString();});
        return collection;
    }

    @Override
    public String getPassword() {
        return member.getKakaoEmail();
    }

    @Override
    public String getUsername() {
        return member.getKakaoNickname();
    }

    // 계정 만료됐는지 확인 -> true 면 아니요.
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정 잠겼는지 확인 -> true 면 아니요.
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 계정 만료됐는지 확인 -> true 면 아니요.
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정 만료됐는지 확인 -> true 면 아니요.
    @Override
    public boolean isEnabled() {
        return true;
    }

}
