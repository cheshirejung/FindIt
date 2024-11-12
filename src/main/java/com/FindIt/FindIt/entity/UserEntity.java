package com.FindIt.FindIt.entity;

import com.FindIt.FindIt.dto.UserDto;
import com.FindIt.FindIt.global.auditing.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@ToString
@Table(name = "user_entity") // 테이블 이름 지정
public class UserEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "login_id", nullable = false)
    private String loginId; // user가 로그인할 때 필요한 id

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "role", nullable = false)
    private String role;

    public UserDto toDto(UserEntity user) {
        return UserDto.builder()
                .loginId(user.getLoginId())
                .userName(user.getUserName())
                .email(user.getEmail())
                .build();
    }
}
