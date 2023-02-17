package com.freeForm.service;

import com.freeForm.config.JwtService;
import com.freeForm.dto.request.AuthenticationRequest;
import com.freeForm.dto.request.RegisterRequest;
import com.freeForm.dto.response.AuthenticationResponse;
import com.freeForm.entity.CustomUserDetails;
import com.freeForm.entity.User;
import com.freeForm.enums.Role;
import com.freeForm.exceptions.UserNameTakenException;
import com.freeForm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest registerRequest) {
        if (registerRequest == null || (!registerRequest.getPassword().equals(registerRequest.getConfirmPassword()))) {
            throw new RuntimeException("Password and Confirm Password must be same");
        }
        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());
        User user = User.builder()
                .firstname(registerRequest.getFirstname())
                .lastname(registerRequest.getLastname())
                .email(registerRequest.getEmail())
                .role(Role.USER)
                .password(encodedPassword)
                .build();
        try {
            userRepository.save(user);
            var jwtToken = jwtService.generateToken(new CustomUserDetails(user));
            return AuthenticationResponse.builder()
                    .authenticationToken(jwtToken)
                    .build();
        } catch (Exception e) {
            throw new UserNameTakenException("Username is already taken");
        }
    }

    public AuthenticationResponse login(AuthenticationRequest request) {
        authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getEmail(),
                                request.getPassword()
                        )
                );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        var jwtToken = jwtService.generateToken(new CustomUserDetails(user));
        return AuthenticationResponse.builder()
                .authenticationToken(jwtToken)
                .build();
    }
}
