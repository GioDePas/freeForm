package com.freeForm.controller;

import com.freeForm.dto.TaskDto;
import com.freeForm.service.TaskService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    @SecurityRequirement(name = "bearerAuth")
    public List<TaskDto> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public TaskDto getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PostMapping
    @SecurityRequirement(name = "bearerAuth")
    public TaskDto createTask(
            @Valid
            @RequestPart TaskDto taskDto,
            @RequestPart MultipartFile file
    ) throws Exception {
        return taskService.createTask(taskDto, file);
    }

    @PutMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public TaskDto updateTask(
            @PathVariable Long id,
            @RequestPart TaskDto taskDto,
            @RequestPart MultipartFile file
    ) throws Exception {
        return taskService.updateTask(id, taskDto, file);
    }

    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}
