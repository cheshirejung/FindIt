package com.FindIt.FindIt.dto;

import com.FindIt.FindIt.entity.UserTestEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;


public class CustomUserDetails implements UserDetails {
    private final UserTestEntity user;

    public CustomUserDetails(UserTestEntity user) {
        this.user = user;
    }
    /*user 권한을 가져옴*/
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new GrantedAuthority()
        {
            @Override
            public String getAuthority(){
                return user.getRole();
            }
        });
        return authorities;
    }
    /*user 비밀번호를 가져옴*/
    @Override
    public String getPassword() {
        return user.getPassword();
    }
    /*user 로그인 아이디를 가져옴*/
    @Override
    public String getUsername() {
        return user.getLoginId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
