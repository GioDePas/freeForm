package com.freeForm.error.web;

import com.freeForm.error.ErrorResponse;
import com.freeForm.error.ErrorResponseList;
import com.freeForm.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class AdviceController {

    //VALIDATION
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseList> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<ErrorResponse> errorsList = ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> ErrorResponse
                        .builder()
                        .code(fieldError.getCode())
                        .message(fieldError.getDefaultMessage())
                        .params(fieldError.getField())
                        .build()
                ).collect(Collectors.toList());
        errorsList.addAll(
                ex
                        .getBindingResult()
                        .getGlobalErrors()
                        .stream()
                        .map(classError -> ErrorResponse
                                .builder()
                                .code(classError.getCode())
                                .message(classError.getDefaultMessage())
                                .params(classError.getObjectName())
                                .build()
                        ).toList()
        );
        return ResponseEntity.badRequest().body(ErrorResponseList
                .builder()
                .errors(errorsList)
                .path(request.getContextPath() + request.getRequestURI())
                .build());
    }

    //MULTIPART
    @ExceptionHandler(MissingServletRequestPartException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponseList> handleMissingPartException(MissingServletRequestPartException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .code("MP009")
                .message("Required part '" + ex.getRequestPartName() + "' is not present.")
                .params(ex.getRequestPartName())
                .build();
        ErrorResponseList errorResponseList = ErrorResponseList.builder()
                .errors(Collections.singletonList(errorResponse))
                .build();
        return ResponseEntity.badRequest().body(errorResponseList);
    }

    //RESOURCE NOT FOUND
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponseList> handleMissingPartException(ResourceNotFoundException ex, HttpServletRequest request) {
        ex.getErrorResponseList().setPath(request.getContextPath() + request.getRequestURI());
        return ResponseEntity.badRequest().body(ex.getErrorResponseList());
    }

    //USER NOT FOUND
    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponseList> handleMissingPartException(UsernameNotFoundException ex, HttpServletRequest request) {
        ErrorResponseList errorResponseList = ErrorResponseList
                .builder()
                .errors(List.of(ErrorResponse
                        .builder()
                        .message(ex.getMessage())
                        .build()))
                .build();
        errorResponseList.setPath(request.getContextPath() + request.getRequestURI());
        return ResponseEntity.badRequest().body(errorResponseList);
    }

    //USERNAME TAKEN
    @ExceptionHandler(UserNameTakenException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponseList> handleMissingPartException(UserNameTakenException ex, HttpServletRequest request) {
        ex.getErrorResponseList().setPath(request.getContextPath() + request.getRequestURI());
        return ResponseEntity.badRequest().body(ex.getErrorResponseList());
    }

}
