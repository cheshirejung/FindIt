package com.FindIt.FindIt.globale.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf((auth) -> auth.disable());

        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/user/login","/user/signup").permitAll()
                        .anyRequest().authenticated()
                );
        http
                .formLogin((auth) -> auth.loginPage("/user/login")
                        .loginProcessingUrl("/loginProc")
                        .usernameParameter("loginId")  // 인자값을 loginId로 설정
                        .defaultSuccessUrl("/home", true) // 로그인 성공 시 리다이렉트할 경로
                        .failureUrl("/user/login?error=true") // 로그인 실패 시 리다이렉트할 경로
                        .permitAll());
        http
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));



        return http.build();
    }
}
