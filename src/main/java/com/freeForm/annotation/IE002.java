package com.freeForm.annotation;

import jakarta.validation.Constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = com.freeForm.validator.EmailValidator.class)
public @interface IE002 {
    String message() default "The email format is invalid";
    Class<?>[] groups() default {};
    Class<?>[] payload() default {};
}
