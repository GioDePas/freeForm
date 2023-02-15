package com.freeForm.service;

import com.freeForm.dto.request.RegisterRequest;
import com.freeForm.entity.User;
import com.freeForm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public Optional<User> getUserByUsername(String email) {
        return userRepository.findByEmail(email);
    }

    public User updateUser(Long id, User user) {
        User currentUser = userRepository.findById(id).orElse(null);
        if (currentUser != null) {
            currentUser.setFirstname(user.getFirstname());
            currentUser.setLastname(user.getLastname());
            currentUser.setEmail(user.getEmail());
            currentUser.setPassword(user.getPassword());
        }
        return userRepository.save(currentUser);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
