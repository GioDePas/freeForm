package com.freeForm.user.service;

import com.freeForm.user.dto.UserDto;
import com.freeForm.user.dao.User;
import com.freeForm.error.ErrorCodes;
import com.freeForm.error.ErrorResponse;
import com.freeForm.error.ErrorResponseList;
import com.freeForm.exception.ResourceNotFoundException;
import com.freeForm.user.mapper.UserMapper;
import com.freeForm.user.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return UserMapper.mapUsersToDto(users);
    }

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

    public Optional<User> getUserByUsername(String email) {
        return userRepository.findByEmail(email);
    }

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

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
