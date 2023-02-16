package com.freeForm.service;

import com.freeForm.config.JwtService;
import com.freeForm.dto.request.RegisterRequest;
import com.freeForm.dto.response.AuthenticationResponse;
import com.freeForm.entity.User;
import com.freeForm.enums.Role;
import com.freeForm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
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
        userRepository.save(user);
        var jwtToken = jwtService.generateToken((UserDetails) user);
        return AuthenticationResponse.builder()
                .authenticationToken(jwtToken)
                .build();
    }

    public AuthenticationResponse login(User user) {
        Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                user.getEmail(),
                                user.getPassword()
                        )
                );
        var username = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        var jwtToken = jwtService.generateToken((UserDetails) username);
        return AuthenticationResponse.builder()
                .authenticationToken(jwtToken)
                .build();
    }
}
