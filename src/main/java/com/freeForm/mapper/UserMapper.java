package com.freeForm.mapper;

import com.freeForm.dto.UserDto;
import com.freeForm.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserDto mapUserToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstname(user.getFirstname());
        userDto.setLastname(user.getLastname());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setRole(user.getRole());
        return userDto;
    }

    public static List<UserDto> mapUsersToDto(List<User> users) {
        return users.stream().map(UserMapper::mapUserToDto).collect(Collectors.toList());
    }

}
