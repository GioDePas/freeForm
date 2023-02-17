package com.freeForm.errors;

public class UserNameTakenException extends RuntimeException{
    public UserNameTakenException(String message) {
        super(message);
    }
}
