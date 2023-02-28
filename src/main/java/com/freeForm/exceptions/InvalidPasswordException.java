package com.freeForm.exceptions;

import com.freeForm.errors.ErrorResponseList;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class InvalidPasswordException extends RuntimeException{
    ErrorResponseList errorResponseList;
}
