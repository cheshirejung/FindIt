package com.FindIt.FindIt.service;

import com.FindIt.FindIt.dto.CustomUserDetails;
import com.FindIt.FindIt.entity.UserEntity;
import com.FindIt.FindIt.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByLoginId(loginId).orElseThrow(() -> new UsernameNotFoundException(loginId));

        if(user != null) {
            return new CustomUserDetails(user);
        }

        return null;
    }
}
