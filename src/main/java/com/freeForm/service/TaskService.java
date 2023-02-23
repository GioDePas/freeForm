package com.freeForm.service;

import com.freeForm.dto.TaskDto;
import com.freeForm.entity.Attachment;
import com.freeForm.entity.Task;
import com.freeForm.mapper.TaskMapper;
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

    public List<TaskDto> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return TaskMapper.mapTasksToDtos(tasks);
    }

    public TaskDto getTaskById(Long id) {
        Task task = taskRepository.findById(id).orElse(null);
        return TaskMapper.mapTaskToDto(task);
    }

    public TaskDto createTask(Task task, MultipartFile file) throws Exception {
        Attachment attachment = new Attachment();
        attachment.setName(file.getOriginalFilename());
        attachment.setData(file.getBytes());
        attachment.setContentType(file.getContentType());
        task.setAttachment(attachment);
        Task taskCreated = taskRepository.save(task);
        return TaskMapper.mapTaskToDto(task);
    }

    public TaskDto updateTask(Long id, Task task, MultipartFile file) throws Exception {
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
        Task updatedTask = taskRepository.save(currentTask);
        return TaskMapper.mapTaskToDto(updatedTask);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

}
