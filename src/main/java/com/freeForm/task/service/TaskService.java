package com.freeForm.task.service;

import com.freeForm.task.dto.TaskDto;
import com.freeForm.attachment.dao.Attachment;
import com.freeForm.task.dao.Task;
import com.freeForm.error.ErrorCodes;
import com.freeForm.error.ErrorResponse;
import com.freeForm.error.ErrorResponseList;
import com.freeForm.exception.ResourceNotFoundException;
import com.freeForm.task.mapper.TaskMapper;
import com.freeForm.task.repo.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public List<TaskDto> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return TaskMapper.mapTasksToDto(tasks);
    }

    public TaskDto getTaskById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                ErrorResponseList
                        .builder()
                        .errors(List.of(ErrorResponse
                                .builder()
                                .message(ErrorCodes.RESOURCE_NOT_FOUND.getMessage())
                                .code(ErrorCodes.RESOURCE_NOT_FOUND.getCode())
                                .build()))
                        .build()));
        return TaskMapper.mapTaskToDto(task);
    }

    public TaskDto createTask(TaskDto taskDto, MultipartFile file) throws IOException {

        Attachment attachment = new Attachment();
        attachment.setName(file.getOriginalFilename());
        attachment.setData(file.getBytes());
        attachment.setContentType(file.getContentType());

        Task task = TaskMapper.mapDtoToTask(taskDto);
        task.setAttachment(attachment);
        Task taskCreated = taskRepository.save(task);

        return TaskMapper.mapTaskToDto(taskCreated);
    }

    public TaskDto updateTask(Long id, TaskDto taskDto, MultipartFile file) throws IOException {
        Task currentTask = taskRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        ErrorResponseList
                                .builder()
                                .errors(List.of(ErrorResponse
                                        .builder()
                                        .message(ErrorCodes.RESOURCE_NOT_FOUND.getMessage())
                                        .code(ErrorCodes.RESOURCE_NOT_FOUND.getCode())
                                        .build()))
                                .build()));
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

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
