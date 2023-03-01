package com.freeForm.service;

import com.freeForm.config.JwtService;
import com.freeForm.dto.AuthenticationDto;
import com.freeForm.dto.UserDto;
import com.freeForm.dto.AuthenticationRequest;
import com.freeForm.entity.CustomUserDetails;
import com.freeForm.entity.User;
import com.freeForm.enums.ErrorCodes;
import com.freeForm.enums.Role;
import com.freeForm.errors.ErrorResponse;
import com.freeForm.errors.ErrorResponseList;
import com.freeForm.exceptions.*;
import com.freeForm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationDto register(UserDto userDto) {

        String encodedPassword = passwordEncoder.encode(userDto.getPassword());

        User user = User.builder()
                .firstname(userDto.getFirstname())
                .lastname(userDto.getLastname())
                .email(userDto.getEmail())
                .role(Role.USER)
                .password(encodedPassword)
                .build();

        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new UserNameTakenException(
                    ErrorResponseList
                            .builder()
                            .errors(List.of(ErrorResponse
                                    .builder()
                                    .message(ErrorCodes.USERNAME_TAKEN.getMessage())
                                    .code(ErrorCodes.USERNAME_TAKEN.getCode())
                                    .build()))
                            .build());
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
            throw new InvalidCredentialsException(
                    ErrorResponseList
                            .builder()
                            .errors(List.of(ErrorResponse
                                    .builder()
                                    .message(ErrorCodes.INVALID_CREDENTIALS.getMessage())
                                    .code(ErrorCodes.INVALID_CREDENTIALS.getCode())
                                    .build()))
                            .build());
        }

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException(
                        ErrorResponseList
                                .builder()
                                .errors(List.of(ErrorResponse
                                        .builder()
                                        .message(ErrorCodes.USERNAME_NOT_FOUND.getCode())
                                        .code(ErrorCodes.USERNAME_NOT_FOUND.getCode())
                                        .build()))
                                .build()));

        var jwtToken = jwtService.generateToken(new CustomUserDetails(user));
        return AuthenticationDto.builder()
                .authenticationToken(jwtToken)
                .build();
    }

}
