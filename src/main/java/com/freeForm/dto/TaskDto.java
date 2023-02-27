package com.freeForm.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private Long id;
    @NotNull(message = "Name is required")
    @Size(max = 20)
    private String name;
    @NotNull(message = "Effort is required")
    @Min(value = 1, message = "Effort must be greater than 0")
    @Max(value = 10, message = "Effort must be less than 10")
    private Integer effort;
}
