package com.FindIt.FindIt.mapper;

import com.FindIt.FindIt.dto.UserSignupDto;
import com.FindIt.FindIt.entity.UserEntity;
import org.springframework.security.crypto.password.PasswordEncoder;


public interface UserMapper {
    UserEntity toEntity(UserSignupDto dto, PasswordEncoder passwordEncoder);
}
