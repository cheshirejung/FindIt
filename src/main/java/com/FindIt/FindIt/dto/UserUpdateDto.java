package com.FindIt.FindIt.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//
//
//
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDto {
        @NotBlank(message = "아이디는 공백일 수 없습니다.")
        private String loginId;

        @NotBlank(message = "이름은 공백일 수 없습니다.")
        private String userName;

        @NotBlank(message = "이메일은 공백일 수 없습니다.")
        @Email(message = "올바른 이메일 형식이어야 합니다.")
        private String email;

        @NotBlank(message="비밀번호는 공백일 수 없습니다.")
        @Pattern(regexp="^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{8,15}", message = "비밀번호는 영어와 숫자로 포함해서 8~15자리 이내로 입력해주세요.")
        private String password;

        @NotBlank(message="비밀번호를 다시한번 입력해주셔야합니다")
        private String rePassword;

}


