package com.freeForm.dto;

import com.freeForm.enums.Role;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    @NotNull(message = "First name is required")
    @Size(min = 2, max = 20)
    private String firstname;
    @NotNull(message = "Last name is required")
    @Size(min = 2, max = 20)
    private String lastname;
    @NotNull(message = "Email is required")
    private String email;
    @NotNull(message = "Password is required")
    private String password;

    private String confirmPassword;
    private Role role;
}
