package com.freeForm.service;

import com.freeForm.dto.TaskDto;
import com.freeForm.entity.Attachment;
import com.freeForm.entity.Task;
import com.freeForm.mapper.TaskMapper;
import com.freeForm.repository.TaskRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    @Transactional(readOnly = true)
    public List<TaskDto> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return TaskMapper.mapTasksToDtos(tasks);
    }

    @Transactional(readOnly = true)
    public TaskDto getTaskById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task with id " + id + " not found"));
        return TaskMapper.mapTaskToDto(task);
    }

    @Transactional
    public TaskDto createTask(@Valid TaskDto taskDto, MultipartFile file) throws Exception {
        Attachment attachment = new Attachment();
        attachment.setName(file.getOriginalFilename());
        attachment.setData(file.getBytes());
        attachment.setContentType(file.getContentType());
        Task task = TaskMapper.mapDtoToTask(taskDto);
        Task taskCreated = taskRepository.save(task);
        return TaskMapper.mapTaskToDto(taskCreated);
    }

    @Transactional
    public TaskDto updateTask(Long id, TaskDto taskDto, MultipartFile file) throws Exception {
        Task currentTask = taskRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Task with id " + id + " not found"));
        Task taskToUpdate = TaskMapper.mapDtoToTask(taskDto);
        if (taskToUpdate.getName() != null) {
            currentTask.setName(taskToUpdate.getName());
        }
        if (taskToUpdate.getEffort() != null) {
            currentTask.setEffort(taskToUpdate.getEffort());
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

    @Transactional
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
