package com.freeForm.exceptions;

import com.freeForm.errors.ErrorResponseList;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ResourceNotFoundException extends RuntimeException {
    private ErrorResponseList errorResponseList;
}
