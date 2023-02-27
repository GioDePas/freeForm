package com.freeForm.service;

import com.freeForm.config.JwtService;
import com.freeForm.dto.AuthenticationDto;
import com.freeForm.dto.UserDto;
import com.freeForm.dto.AuthenticationRequest;
import com.freeForm.entity.CustomUserDetails;
import com.freeForm.entity.User;
import com.freeForm.enums.Role;
import com.freeForm.exceptions.*;
import com.freeForm.repository.UserRepository;
import com.freeForm.utils.ValidationUtils;
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

    public AuthenticationDto register(UserDto userDto) {
        if (userDto == null || (!userDto.getPassword().equals(userDto.getConfirmPassword()))) {
            throw new PasswordMismatchException("Password and Confirm Password must be the same");
        }

        String encodedPassword = passwordEncoder.encode(userDto.getPassword());

        User user = User.builder()
                .firstname(userDto.getFirstname())
                .lastname(userDto.getLastname())
                .email(userDto.getEmail())
                .role(Role.USER)
                .password(encodedPassword)
                .build();

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserNameTakenException("Username: " + user.getEmail() + " is already taken");
        }

        if (!ValidationUtils.isValidEmail(user.getEmail())) {
            throw new InvalidEmailException("Invalid email: " + user.getEmail());
        }

        userRepository.save(user);

        var jwtToken = jwtService.generateToken(new CustomUserDetails(user));
        return AuthenticationDto.builder()
                .authenticationToken(jwtToken)
                .build();
    }

    public AuthenticationDto login(AuthenticationRequest request) {

        try {
            authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    request.getEmail(),
                                    request.getPassword()
                            )
                    );
        } catch (Exception e) {
            throw new InvalidCredentialsException("Invalid username or password");
        }

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        var jwtToken = jwtService.generateToken(new CustomUserDetails(user));
        return AuthenticationDto.builder()
                .authenticationToken(jwtToken)
                .build();
    }
}
