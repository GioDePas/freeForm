package com.freeForm.service;

import com.freeForm.entity.Attachment;
import com.freeForm.entity.Task;
import com.freeForm.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task task, MultipartFile file) throws Exception {
        Task currentTask = taskRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Task with id " + id + " not found"));
        if (task.getName() != null) {
            currentTask.setName(task.getName());
        }
        if (task.getEffort() != null) {
            currentTask.setEffort(task.getEffort());
        }
        if (file != null) {
            Attachment attachment = currentTask.getAttachment();
            attachment.setName(file.getOriginalFilename());
            attachment.setData(file.getBytes());
            attachment.setContentType(file.getContentType());
            currentTask.setAttachment(attachment);
        }
        return taskRepository.save(currentTask);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }


}
