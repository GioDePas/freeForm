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
    @NotNull
    @Size(min = 2, max = 20)
    private String firstname;
    @NotNull
    @Size(min = 2, max = 20)
    private String lastname;
    @NotNull
    @Email
    private String email;
    @NotNull
    private Integer age;
    @NotNull
    private List<@Valid TaskDto> tasks;

}
