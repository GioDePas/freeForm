package com.freeForm.controller;

import com.freeForm.dto.TaskDto;
import com.freeForm.entity.Attachment;
import com.freeForm.entity.Task;
import com.freeForm.mapper.TaskMapper;
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
        List<Task> tasks = taskService.getAllTasks();
        return TaskMapper.mapTasksToDtos(tasks);
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public TaskDto getTaskById(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        return TaskMapper.mapTasksToDto(task);
    }

    @PostMapping
    @SecurityRequirement(name = "bearerAuth")
    public TaskDto createTask(
            @Valid
            @RequestPart Task task,
            @RequestPart MultipartFile file
    ) throws Exception {
        Attachment attachment = new Attachment();
        attachment.setName(file.getOriginalFilename());
        attachment.setData(file.getBytes());
        attachment.setContentType(file.getContentType());
        task.setAttachment(attachment);
        Task createdTask = taskService.createTask(task);
        return TaskMapper.mapTasksToDto(createdTask);
    }

    @PutMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public TaskDto updateTask(
            @PathVariable Long id,
            @RequestPart Task task,
            @RequestPart MultipartFile file
    ) throws Exception {
        Task updatedTask = taskService.updateTask(id, task, file);
        return TaskMapper.mapTasksToDto(updatedTask);
    }

    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

}
