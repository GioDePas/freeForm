package com.freeForm.task.web;

import com.freeForm.task.dto.TaskDto;
import com.freeForm.task.service.TaskService;
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
    private static final String ID_PATH = "/{id}";
    private static final String BEARER = "bearerAuth";

    @GetMapping
    @SecurityRequirement(name = BEARER)
    public List<TaskDto> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping(ID_PATH)
    @SecurityRequirement(name = BEARER)
    public TaskDto getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PostMapping
    @SecurityRequirement(name = BEARER)
    public TaskDto createTask(
            @Valid
            @RequestPart TaskDto taskDto,
            @RequestPart MultipartFile file
    ) throws Exception {
        return taskService.createTask(taskDto, file);
    }

    @PutMapping(ID_PATH)
    @SecurityRequirement(name = BEARER)
    public TaskDto updateTask(
            @PathVariable Long id,
            @RequestPart TaskDto taskDto,
            @RequestPart MultipartFile file
    ) throws Exception {
        return taskService.updateTask(id, taskDto, file);
    }

    @DeleteMapping(ID_PATH)
    @SecurityRequirement(name = BEARER)
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}
