package com.freeForm.error;

import lombok.*;

@Getter
@AllArgsConstructor
public enum ErrorCodes {
    INVALID_CREDENTIALS("IC001", "Invalid credentials"),
    //INVALID_EMAIL("IE002", "Invalid email"),
    //PASSWORD_MISMATCH("PM003", "Password mismatch"),
    RESOURCE_NOT_FOUND("RN004", "Resource not found"),
    USERNAME_TAKEN("UT005", "Username already taken"),
    //ILLEGAL_ARGUMENT("IA006", "Illegal argument"),
    USERNAME_NOT_FOUND("UN007", "Username not found");
    //INVALID_PASSWORD("IP008", "Invalid password");
    private final String code;
    private final String message;
}
