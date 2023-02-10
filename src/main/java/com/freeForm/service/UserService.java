package com.freeForm.service;

import com.freeForm.dto.request.RegisterRequest;
import com.freeForm.entity.User;
import com.freeForm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User createUser(RegisterRequest registerRequest) {
        if (registerRequest == null || (!registerRequest.getPassword().equals(registerRequest.getConfirmPassword()))) {
            return null;
        }
        String encodedPassword = bCryptPasswordEncoder.encode(registerRequest.getPassword());
        User user = User.builder().username(registerRequest.getUsername()).password(encodedPassword).build();
        return userRepository.save(user);
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User updateUser(Long id, User user) {
        User currentUser = userRepository.findById(id).orElse(null);
        if (currentUser != null) {
            currentUser.setUsername(user.getUsername());
            currentUser.setPassword(user.getPassword());
        }
        return userRepository.save(currentUser);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
