package com.FindIt.FindIt.repository;



import com.FindIt.FindIt.entity.UserTestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserTestEntity, Integer> {
    Optional<UserTestEntity> findByLoginId(String loginId);
}
