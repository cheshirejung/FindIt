package com.FindIt.FindIt.dto;

import com.FindIt.FindIt.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class UserDto {
    // UserDto.java
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class UserDto {
        private String loginId;
        private String userName;
        private String email;

        // Entity -> DTO 변환을 위한 생성자
        public UserDto(UserEntity user) {
            this.loginId = user.getLoginId();
            this.userName = user.getUserName();
            this.email = user.getEmail();
        }
    }

    // UserUpdateDto.java
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

}
