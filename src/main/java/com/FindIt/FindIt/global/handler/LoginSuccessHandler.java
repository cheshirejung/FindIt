package com.FindIt.FindIt.global.handler;

import com.FindIt.FindIt.dto.CustomUserDetails;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

@Slf4j
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
        log.info("아이디 : {}", user.getUsername()); //로그인한 유저 아이디를 로그 출력
        log.info("권한 : {}", user.getAuthorities().iterator().next().getAuthority()); //로그인한 유저 권한 로그 추력
        response.sendRedirect("/board"); //로그인 성공 후 임시 메인페이지로 리다이렉트

    }
}
