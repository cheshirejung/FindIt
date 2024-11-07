package com.FindIt.FindIt.repository;


import com.FindIt.FindIt.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByLoginId(String loginId); // login_id로 사용자 조회 메서드
}
