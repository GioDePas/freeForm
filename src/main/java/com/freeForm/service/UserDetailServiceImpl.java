package com.freeForm.service;

import com.freeForm.entity.User;
import com.freeForm.entity.CustomUserDetails;
import com.freeForm.errors.ErrorCodes;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userService.getUserByUsername(email);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException(ErrorCodes.RESOURCE_NOT_FOUND.getMessage()
                    + " " + ErrorCodes.RESOURCE_NOT_FOUND.getCode());
        }
        return new CustomUserDetails(user.get());
    }
}