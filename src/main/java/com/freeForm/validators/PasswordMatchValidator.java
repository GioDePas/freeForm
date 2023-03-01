package com.freeForm.validators;

import com.freeForm.annotations.PM003;
import com.freeForm.dto.UserDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PM003, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value instanceof UserDto userDto) {
            String password = userDto.getPassword();
            String confirmPassword = userDto.getConfirmPassword();
            return password != null && password.equals(confirmPassword);
        }
        return false;
    }

}
