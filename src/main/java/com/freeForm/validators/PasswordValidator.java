package com.freeForm.validators;

import com.freeForm.annotations.IP008;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<IP008, String> {
    public static final String PASSWORD_PATTERN = "^(?=.*[A-Z])(?=.*[_@#$%^&+=-])(?=.*[0-9])(?=\\S+$).{8,}$";

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        return password != null && isValidPassword(password);
    }

    private boolean isValidPassword(String password) {
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        return pattern.matcher(password).matches();
    }
}
