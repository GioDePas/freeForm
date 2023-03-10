package com.freeForm.controller;

import com.freeForm.dto.UserDto;
import com.freeForm.dto.AuthenticationRequest;
import com.freeForm.dto.AuthenticationDto;
import com.freeForm.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationDto> register(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(authService.register(userDto));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationDto> login(@RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok().body(authService.login(authenticationRequest));
    }
}
