package com.freeForm.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkerDto {
    private Long id;
    @NotNull(message = "First name is required")
    @Size(min = 2, max = 20)
    private String firstname;
    @NotNull(message = "Last name is required")
    @Size(min = 2, max = 20)
    private String lastname;
    @NotNull(message = "Email is required")
    private String email;
    @NotNull(message = "Age is required")
    private Integer age;

    private List<@Valid TaskDto> tasks;

}
