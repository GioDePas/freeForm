package com.freeForm.authentication.service;

import com.freeForm.config.JwtService;
import com.freeForm.authentication.dto.AuthDto;
import com.freeForm.user.dto.UserDto;
import com.freeForm.authentication.request.AuthRequest;
import com.freeForm.user.dto.CustomUserDetails;
import com.freeForm.user.dao.User;
import com.freeForm.error.ErrorCodes;
import com.freeForm.config.Role;
import com.freeForm.error.ErrorResponse;
import com.freeForm.error.ErrorResponseList;
import com.freeForm.exception.*;
import com.freeForm.user.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthDto register(UserDto userDto) {

        String encodedPassword = passwordEncoder.encode(userDto.getPassword());

        User user = User.builder()
                .firstname(userDto.getFirstname())
                .lastname(userDto.getLastname())
                .email(userDto.getEmail())
                .role(Role.USER)
                .password(encodedPassword)
                .build();

        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new UserNameTakenException(ErrorResponseList.builder()
                    .errors(List.of(ErrorResponse.builder()
                            .message(ErrorCodes.USERNAME_TAKEN.getMessage())
                            .code(ErrorCodes.USERNAME_TAKEN.getCode())
                            .build()))
                    .build());
        }

        userRepository.save(user);

        var jwtToken = jwtService.generateToken(new CustomUserDetails(user));
        var refreshToken = jwtService.generateRefreshToken(new CustomUserDetails(user));

        return AuthDto.builder()
                .authenticationToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public AuthDto login(AuthRequest request) {

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
        var refreshToken = jwtService.generateRefreshToken(new CustomUserDetails(user));
        return AuthDto.builder()
                .authenticationToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

}
