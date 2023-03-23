package com.freeForm.annotation;

import jakarta.validation.Constraint;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = com.freeForm.validators.PasswordValidator.class)
public @interface IP008 {
    String message() default "The password must contain at least one uppercase letter, one special character and must be at least 8 characters long";
    Class<?>[] groups() default {};
    Class<?>[] payload() default {};
}
