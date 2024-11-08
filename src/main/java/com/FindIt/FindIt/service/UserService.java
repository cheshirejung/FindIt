package com.FindIt.FindIt.service;

import com.FindIt.FindIt.entity.UserEntity;
import com.FindIt.FindIt.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Transactional
    public void registerUser(UserEntity user){
        if (userRepository.findByLoginId(user.getLoginId()).isPresent()) {
            throw new IllegalArgumentException("이미 사용 중인 ID입니다.");
        } // 로그인 id 중복 검사
        user.setRole("user");   // 수정 필요 : 어떻게 관리자와 일반 사용자로 구분할지 의논 필요
        userRepository.save(user);
    }

}
