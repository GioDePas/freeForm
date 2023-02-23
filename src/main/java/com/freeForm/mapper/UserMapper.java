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

    public static List<UserDto> mapUsersToDtos(List<User> users) {
        return users.stream().map(UserMapper::mapUserToDto).collect(Collectors.toList());
    }

    public static User mapDtoToUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        return user;
    }

    public static List<User> mapDtosToUsers(List<UserDto> userDtos) {
        return userDtos.stream().map(UserMapper::mapDtoToUser).collect(Collectors.toList());
    }
}
