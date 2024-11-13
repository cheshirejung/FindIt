package com.FindIt.FindIt.dto;

import com.FindIt.FindIt.entity.UserEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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

    @NotBlank(message = "아이디는 공백일 수 없습니다.")
    private String loginId;

    @NotBlank(message = "이름은 공백일 수 없습니다.")
    private String userName;

    @NotBlank(message = "이메일은 공백일 수 없습니다.")
    @Email(message = "올바른 이메일 형식이어야 합니다.")
    private String email;

    @NotBlank(message="비밀번호는 공백일 수 없습니다.")
    @Pattern(regexp="^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{8,15}", message = "비밀번호는 영어,숫자,특수문자를 포함하여 8~15자리 이내로 입력해주세요.")
    private String password;

    @NotBlank(message="비밀번호를 다시한번 입력해주셔야합니다")
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
