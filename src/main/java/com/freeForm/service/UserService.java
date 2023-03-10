package com.freeForm.service;

import com.freeForm.dto.UserDto;
import com.freeForm.entity.User;
import com.freeForm.enums.ErrorCodes;
import com.freeForm.errors.ErrorResponse;
import com.freeForm.errors.ErrorResponseList;
import com.freeForm.exceptions.ResourceNotFoundException;
import com.freeForm.mapper.UserMapper;
import com.freeForm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return UserMapper.mapUsersToDto(users);
    }

    @Transactional(readOnly = true)
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                ErrorResponseList
                        .builder()
                        .errors(List.of(ErrorResponse
                                .builder()
                                .message(ErrorCodes.RESOURCE_NOT_FOUND.getMessage())
                                .code(ErrorCodes.RESOURCE_NOT_FOUND.getCode())
                                .build()))
                        .build()));
        return UserMapper.mapUserToDto(user);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserByUsername(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    public UserDto updateUser(Long id, User user) {
        User currentUser = userRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        ErrorResponseList
                                .builder()
                                .errors(List.of(ErrorResponse
                                        .builder()
                                        .message(ErrorCodes.RESOURCE_NOT_FOUND.getMessage())
                                        .code(ErrorCodes.RESOURCE_NOT_FOUND.getCode())
                                        .build()))
                                .build()));
        if (user.getFirstname() != null) {
            currentUser.setFirstname(user.getFirstname());
        }
        if (user.getLastname() != null) {
            currentUser.setLastname(user.getLastname());
        }
        if (user.getEmail() != null) {
            currentUser.setEmail(user.getEmail());
        }
        if (user.getPassword() != null) {
            currentUser.setPassword(user.getPassword());
        }
        User updatedUser = userRepository.save(currentUser);
        return UserMapper.mapUserToDto(updatedUser);
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
