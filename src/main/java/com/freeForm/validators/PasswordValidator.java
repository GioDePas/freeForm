package com.freeForm.validators;

import com.freeForm.annotation.IP008;
import com.freeForm.constant.Values;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<IP008, String> {

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        return password != null && isValidPassword(password);
    }

    private boolean isValidPassword(String password) {
        Pattern pattern = Pattern.compile(Values.PASSWORD_PATTERN);
        return pattern.matcher(password).matches();
    }
}
