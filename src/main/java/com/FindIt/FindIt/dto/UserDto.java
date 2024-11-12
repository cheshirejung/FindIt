package com.FindIt.FindIt.dto;

import com.FindIt.FindIt.entity.UserEntity;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String loginId;
    private String userName;
    private String email;
}
