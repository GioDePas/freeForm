package com.freeForm.exception;

import com.freeForm.error.ErrorResponseList;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserNameTakenException extends RuntimeException {
    private ErrorResponseList errorResponseList;
}
