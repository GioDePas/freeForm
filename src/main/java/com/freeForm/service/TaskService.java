package com.freeForm.service;

import com.freeForm.entity.Attachment;
import com.freeForm.entity.Task;
import com.freeForm.repository.AttachmentRepository;
import com.freeForm.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final AttachmentRepository attachmentRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task task) {
        Task currentTask = taskRepository
                .findById(id)
                .orElseThrow(()->new RuntimeException("Task with id " + id + " not found"));
        if (task.getName() != null) {
            currentTask.setName(task.getName());
        }
        if (task.getEffort() != null) {
            currentTask.setEffort(task.getEffort());
        }
        return taskRepository.save(currentTask);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public Attachment addAttachmentToTask(MultipartFile file, Long taskId) throws IOException {
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        if (optionalTask.isEmpty()) {
            throw new RuntimeException("Task with id " + taskId + " not found");
        }
        Task task = optionalTask.get();

        Attachment attachment = new Attachment();
        attachment.setName(file.getOriginalFilename());
        attachment.setContentType(file.getContentType());
        attachment.setData(file.getBytes());
        return attachment;
    }
}
