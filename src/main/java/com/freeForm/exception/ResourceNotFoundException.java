package com.freeForm.exception;

import com.freeForm.error.ErrorResponseList;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ResourceNotFoundException extends RuntimeException {
    private ErrorResponseList errorResponseList;
}
