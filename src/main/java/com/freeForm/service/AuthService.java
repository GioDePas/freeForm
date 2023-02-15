package com.freeForm.service;

import com.freeForm.dto.request.RegisterRequest;
import com.freeForm.entity.User;
import com.freeForm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User createUser(RegisterRequest registerRequest) {

        if (registerRequest == null || (!registerRequest.getPassword().equals(registerRequest.getConfirmPassword()))) {
            return null;
        }
        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());

        User user = User.builder()
                .firstname(registerRequest.getFirstname())
                .lastname(registerRequest.getLastname())
                .email(registerRequest.getEmail())
                .password(encodedPassword)
                .build();

        return userRepository.save(user);
    }
}
