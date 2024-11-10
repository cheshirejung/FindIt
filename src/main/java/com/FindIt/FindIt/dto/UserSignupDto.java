package com.FindIt.FindIt.dto;

import com.FindIt.FindIt.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserSignupDto {

    private String loginId;
    private String userName;
    private String email;
    private String password;
    private String rePassword; // 비밀번호 확인 필드 추가

    public UserEntity toEntity(UserEntity user, PasswordEncoder passwordEncoder) {
        user.setLoginId(this.loginId);
        user.setUserName(this.userName);
        user.setEmail(this.email);
        // 비밀번호 암호화
        user.setPassword(passwordEncoder.encode(this.password));
        user.setRole("USER"); // 디폴트 값으로 일반 유저로 할당
        return user;
    }
}
