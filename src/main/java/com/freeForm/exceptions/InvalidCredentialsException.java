package com.freeForm.exceptions;

import com.freeForm.errors.ErrorResponseList;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class InvalidCredentialsException extends RuntimeException  {
    private ErrorResponseList errorResponseList;
}
