package com.freeForm.validator;

import com.freeForm.annotation.IE002;
import com.freeForm.constant.Values;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<IE002, String> {

    @Override
    public boolean isValid(String  email, ConstraintValidatorContext context) {
        return email != null && isValidEmail(email);
    }

    public boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(Values.EMAIL_PATTERN);
        return pattern.matcher(email).matches();
    }

}
