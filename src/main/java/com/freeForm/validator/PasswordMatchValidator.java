package com.freeForm.validator;

import com.freeForm.annotation.PM003;
import com.freeForm.user.dto.UserDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PM003, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value instanceof UserDto userDto) {
            String password = userDto.getPassword();
            String confirmPassword = userDto.getConfirmPassword();
            return password.equals(confirmPassword);
        }
        return false;
    }

}
