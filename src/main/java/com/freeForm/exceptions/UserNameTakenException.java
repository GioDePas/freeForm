package com.freeForm.exceptions;

public class UserNameTakenException extends RuntimeException {
    public UserNameTakenException(String message) {
        super(message);
    }
}
