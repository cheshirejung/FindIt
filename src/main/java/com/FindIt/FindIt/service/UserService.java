package com.FindIt.FindIt.service;

import com.FindIt.FindIt.dto.CustomUserDetails;
import com.FindIt.FindIt.dto.UserDto;
import com.FindIt.FindIt.dto.UserSignupDto;
import com.FindIt.FindIt.dto.UserUpdateDto;
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

        UserEntity user = userSignupDto.toEntity(new UserEntity(), passwordEncoder);
        userRepository.save(user);
    }

    public UserDto getUser() {
        UserEntity user = userRepository.findLoginUserByLoginId(SecurityContextHolder.getContext().getAuthentication().getName());
        UserDto userDto = user.toDto(user);

        return userDto;
    }

    @Transactional
    public void updateUser(UserUpdateDto userUpdateDto) {
        // 현재 로그인한 사용자 정보 가져오기
        UserEntity user = userRepository.findLoginUserByLoginId(
                SecurityContextHolder.getContext().getAuthentication().getName());

        if (user == null) {
            throw new IllegalArgumentException("사용자를 찾을 수 없습니다.");
        }

        // 비밀번호 변경을 원하는 경우
        if (userUpdateDto.getPassword() != null && !userUpdateDto.getPassword().isEmpty()) {
            // 비밀번호 일치 여부 확인
            if (!userUpdateDto.getPassword().equals(userUpdateDto.getRePassword())) {
                throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
            }
            // 새 비밀번호 암호화
            user.setPassword(passwordEncoder.encode(userUpdateDto.getPassword()));
        }

        // 나머지 정보 업데이트
        user.setUserName(userUpdateDto.getUserName());
        user.setEmail(userUpdateDto.getEmail());

        userRepository.save(user);
    }

    @Transactional
    public boolean deleteUser(UserWithdrawDto userWithdrawDto) {
        UserEntity user = userRepository.findLoginUserByLoginId(userWithdrawDto.getLoginId());
        //UserEntity loginUser = userDetails.getUser(); // 현재 로그인된 사용자
        //UserDto loginUserDto = loginUser.toDto(loginUser); // Dto 변환

        if (user != null && passwordEncoder.matches(userWithdrawDto.getDelPassword(), user.getPassword())) {
            //userRepository.delete(user);
            user.setActive("N");
            return true;
        }
        return false;
    }



}
