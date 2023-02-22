package com.freeForm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkerDto {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private Integer age;
    private List<TaskDto> tasks;

}
