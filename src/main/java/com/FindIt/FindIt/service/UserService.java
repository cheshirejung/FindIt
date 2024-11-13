package com.FindIt.FindIt.service;

import com.FindIt.FindIt.dto.UserDto;
import com.FindIt.FindIt.dto.UserSignupDto;
import com.FindIt.FindIt.dto.UserWithdrawDto;
import com.FindIt.FindIt.entity.UserEntity;
import com.FindIt.FindIt.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void registerUser(UserSignupDto userSignupDto){
        // 로그인 id 중복 검사
        if (userRepository.findByLoginId(userSignupDto.getLoginId()).isPresent()) {
            throw new IllegalArgumentException("이미 사용 중인 ID입니다.");
        }

        // 비밀번호 일치 확인
        if (!userSignupDto.getPassword().equals(userSignupDto.getRePassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        UserEntity user = userSignupDto.toEntity(new UserEntity(), passwordEncoder);
        userRepository.save(user);
    }

    public UserDto getUser() {
        UserEntity user = userRepository.findLoginUserByLoginId(SecurityContextHolder.getContext().getAuthentication().getName());
        UserDto userDto = user.toDto(user);

        return userDto;
    }

    @Transactional
    public boolean deleteUser(UserWithdrawDto userWithdrawDto) {
        UserEntity user = userRepository.findLoginUserByLoginId(userWithdrawDto.getLoginId());

        if (user != null && passwordEncoder.matches(userWithdrawDto.getDelPassword(), user.getPassword())) {
            userRepository.delete(user);
            return true;
        }
        return false;
    }



}
