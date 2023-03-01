package com.freeForm.validators;

import com.freeForm.annotations.IE002;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<IE002, String> {
    public static final String EMAIL_PATTERN =
            "^[a-zA-Z0-9_+&*-]+(?:\\." +
            "[a-zA-Z0-9_+&*-]+)*@" +
            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
            "A-Z]{2,7}$";

    @Override
    public boolean isValid(String  email, ConstraintValidatorContext context) {
        return email != null && isValidEmail(email);
    }

    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        return pattern.matcher(email).matches();
    }

}
