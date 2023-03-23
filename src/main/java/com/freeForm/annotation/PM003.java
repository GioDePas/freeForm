package com.freeForm.annotation;

import com.freeForm.validators.PasswordMatchValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = PasswordMatchValidator.class)
public @interface PM003 {
    String message() default "Password and confirm password must match";
    Class<?>[] groups() default {};
    Class<?>[] payload() default {};
}
