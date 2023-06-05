package com.freeForm.worker.dto;

import com.freeForm.annotation.IE002;
import com.freeForm.task.dto.TaskDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
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
    @IE002
    private String email;
    @NotNull(message = "Age is required")
    @Min(value = 18, message = "Age must be greater than 18")
    @Max(value = 80, message = "Age must be less than 80")
    private Integer age;
    private List<@Valid TaskDto> tasks;
}
