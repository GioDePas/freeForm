package com.freeForm.dto;

import com.freeForm.annotations.IE002;
import com.freeForm.annotations.IP008;
import com.freeForm.annotations.PM003;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@PM003
public class UserDto {
    @NotNull(message = "First name is required")
    @Size(min = 2, max = 20)
    private String firstname;
    @NotNull(message = "Last name is required")
    @Size(min = 2, max = 20)
    private String lastname;
    @NotNull(message = "Email is required")
    @IE002
    private String email;
    @NotNull(message = "Password is required")
    @IP008
    private String password;
    @NotNull(message = "Confirm password is required")
    private String confirmPassword;
}
