package com.freeForm.controller.advice;

import com.freeForm.errors.ErrorResponse;
import com.freeForm.errors.ErrorResponseList;
import com.freeForm.exceptions.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

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
        return ResponseEntity.badRequest().body(ErrorResponseList
                .builder()
                .errors(errorsList)
                .path(request.getContextPath() + request.getRequestURI())
                .build());
    }

    //MULTIPART
    @ExceptionHandler(MissingServletRequestPartException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleMissingPartException(MissingServletRequestPartException ex) {
        return ResponseEntity.badRequest().body("Required part '" + ex.getRequestPartName() + "' is not present.");
    }

    //RESOURCE NOT FOUND
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponseList> handleMissingPartException(ResourceNotFoundException ex, HttpServletRequest request) {
        ex.getErrorResponseList().setPath(request.getContextPath() + request.getRequestURI());
        return ResponseEntity.badRequest().body(ex.getErrorResponseList());
    }

    //INVALID EMAIL
    @ExceptionHandler(InvalidEmailException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponseList> handleMissingPartException(InvalidEmailException ex, HttpServletRequest request) {
        ex.getErrorResponseList().setPath(request.getContextPath() + request.getRequestURI());
        return ResponseEntity.badRequest().body(ex.getErrorResponseList());
    }

    //ILLEGAL ARGUMENT
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponseList> handleMissingPartException(IllegalArgumentException ex, HttpServletRequest request) {
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

    //PASSWORD MISMATCH
    @ExceptionHandler(PasswordMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponseList> handleMissingPartException(PasswordMismatchException ex, HttpServletRequest request) {
        ex.getErrorResponseList().setPath(request.getContextPath() + request.getRequestURI());
        return ResponseEntity.badRequest().body(ex.getErrorResponseList());
    }

    //USERNAME TAKEN
    @ExceptionHandler(UserNameTakenException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponseList> handleMissingPartException(UserNameTakenException ex, HttpServletRequest request) {
        ex.getErrorResponseList().setPath(request.getContextPath() + request.getRequestURI());
        return ResponseEntity.badRequest().body(ex.getErrorResponseList());
    }

    //INVALID PASSWORD
    @ExceptionHandler(InvalidPasswordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponseList> handleMissingPartException(InvalidPasswordException ex, HttpServletRequest request) {
        ex.getErrorResponseList().setPath(request.getContextPath() + request.getRequestURI());
        return ResponseEntity.badRequest().body(ex.getErrorResponseList());
    }

}
