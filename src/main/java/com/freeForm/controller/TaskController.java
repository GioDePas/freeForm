package com.freeForm.controller;

import com.freeForm.entity.Attachment;
import com.freeForm.entity.Task;
import com.freeForm.service.AttachmentService;
import com.freeForm.service.TaskService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;
    private final AttachmentService attachmentService;

    @GetMapping
    @SecurityRequirement(name = "bearerAuth")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PostMapping
    @SecurityRequirement(name = "bearerAuth")
    public Task createTask(@Valid @RequestPart Task task, @RequestPart MultipartFile file) throws Exception {
        Attachment attachment = new Attachment();
        attachment.setName(file.getOriginalFilename());
        attachment.setData(file.getBytes());
        return taskService.createTask(task);
    }

    @PutMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task) {
        return taskService.updateTask(id, task);
    }

    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

    @PostMapping("/tasks/{taskId}/attachment")
    public ResponseEntity<Attachment> createAttachmentByTaskId(@PathVariable Long taskId, @RequestBody Attachment attachment) {
        Task task = taskService.getTaskById(taskId);
        if (task == null) {
            return ResponseEntity.notFound().build();
        }
        task.setAttachment(attachment);
        attachmentService.createAttachment(attachment);
        return ResponseEntity.ok(attachment);
    }

}
