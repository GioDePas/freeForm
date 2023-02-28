package com.freeForm.enums;

import lombok.*;

@Getter
@AllArgsConstructor
public enum ErrorCodes {
    INVALID_CREDENTIALS("ERR001", "Invalid credentials"),
    INVALID_EMAIL("ERR002", "Invalid email"),
    PASSWORD_MISMATCH("ERR003", "Password mismatch"),
    RESOURCE_NOT_FOUND("ERR004", "Resource not found"),
    USERNAME_TAKEN("ERR005", "Username already taken"),
    ILLEGAL_ARGUMENT("ERR006", "Illegal argument"),
    USERNAME_NOT_FOUND("ERR007", "Username not found");

    private final String code;
    private final String message;
}
