package com.FindIt.FindIt.dto;

import com.FindIt.FindIt.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;


public class CustomUserDetails implements UserDetails {
    private final UserEntity user;

    public CustomUserDetails(UserEntity user) {
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

    /* role을 확인하기 위해 추가 */
    public Object getRole() {
        return user.getRole();
    }

    /*UserEntity를 DB 조회없이 가져옴*/
    public UserEntity getUser() {
        return user;
    }
    /*active 상태를 가져옴*/
    public String getActive(){
        return user.getActive();
    }

}
