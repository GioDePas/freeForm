package com.freeForm.controller;

import com.freeForm.dto.request.AuthenticationRequest;
import com.freeForm.dto.request.RegisterRequest;
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
    public ResponseEntity<AuthenticationDto> register(@Valid @RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authService.register(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationDto> login(@RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok().body(authService.login(authenticationRequest));
    }
}
