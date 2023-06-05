package com.freeForm.authentication.web;

import com.freeForm.user.dto.UserDto;
import com.freeForm.authentication.request.AuthRequest;
import com.freeForm.authentication.dto.AuthDto;
import com.freeForm.authentication.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final static String REGISTER_PATH = "/register";
    private final static String LOGIN_PATH = "/login";

    @PostMapping(REGISTER_PATH)
    public ResponseEntity<AuthDto> register(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(authService.register(userDto));
    }

    @PostMapping(LOGIN_PATH)
    public ResponseEntity<AuthDto> login(@RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok().body(authService.login(authRequest));
    }
}
