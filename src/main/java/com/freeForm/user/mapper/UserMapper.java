package com.freeForm.user.mapper;

import com.freeForm.user.dto.UserDto;
import com.freeForm.user.dao.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserDto mapUserToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setFirstname(user.getFirstname());
        userDto.setLastname(user.getLastname());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        return userDto;
    }

    public static List<UserDto> mapUsersToDto(List<User> users) {
        return users.stream().map(UserMapper::mapUserToDto).collect(Collectors.toList());
    }

}
