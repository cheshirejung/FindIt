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
        private String loginId;
        private String userName;
        private String email;
        private String password;
        private String rePassword;
}


