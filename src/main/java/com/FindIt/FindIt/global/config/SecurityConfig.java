package com.FindIt.FindIt.global.config;

import com.FindIt.FindIt.global.handler.LoginFailHandler;
import com.FindIt.FindIt.global.handler.LoginSuccessHandler;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

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
                .csrf(AbstractHttpConfigurer::disable);
        /*비로그인 사용자는 사이트 접속 시 로그인 페이지와 회원가입 페이지만 사용할 수 있게 설정*/
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/user/login","/user/signup","/css/**","/js/**").permitAll()
                        .requestMatchers("/board/create").hasRole("ADMIN")
                        .anyRequest().authenticated()
                );


        /*시큐리티 기본 로그인 폼에서 커스텀 로그인 폼으록 변경하여 사용*/
        /*비로그인 사용자는 사이트 접속 시 로그인 페이지로 리다이렉트 되도록 설정*/
        http
                .formLogin((auth) -> auth.loginPage("/user/login")
                        .loginProcessingUrl("/user/login") //스프링 시큐리티 기본 로그인 요청 url에서 지정 url로 변경
                        .usernameParameter("loginId")  // 기본 인자값이 username로 설정되어 있어 인자값을 loginId로 설정
                        .defaultSuccessUrl("/board", true) // 로그인 성공 시 리다이렉트할 경로
                        .successHandler(new LoginSuccessHandler())
                        .failureHandler(loginFailHandler())
                        .permitAll());
        http
                .logout((auth) -> auth.logoutUrl("/user/logout")
                        .logoutSuccessUrl("/user/login")
                        .addLogoutHandler((request, response, authentication) -> {
                            HttpSession session = request.getSession();
                            session.invalidate();
                        })
                        .logoutSuccessHandler((request, response, authentication) -> response.sendRedirect("/user/login"))
                        .deleteCookies("JSESSIONID")
                );
        http
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));



        return http.build();
    }
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new LoginSuccessHandler();
    }

    @Bean
    public LoginFailHandler loginFailHandler(){
        return new LoginFailHandler();
    }
}
