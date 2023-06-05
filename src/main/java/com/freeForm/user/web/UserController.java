package com.freeForm.user.web;

import com.freeForm.user.dto.UserDto;
import com.freeForm.user.dao.User;
import com.freeForm.user.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private static final String ID_PATH = "/{id}";
    private static final String BEARER = "bearerAuth";

    @GetMapping
    @SecurityRequirement(name = BEARER)
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(ID_PATH)
    @SecurityRequirement(name = BEARER)
    public UserDto getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping(ID_PATH)
    @SecurityRequirement(name = BEARER)
    public UserDto updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping(ID_PATH)
    @SecurityRequirement(name = BEARER)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
