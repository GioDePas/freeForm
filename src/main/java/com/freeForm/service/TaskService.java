package com.freeForm.service;

import com.freeForm.entity.Task;
import com.freeForm.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Task updateTask(Long id, Task task) {
        Task currentTask = taskRepository.findById(id).orElse(null);
        if (currentTask != null) {
            currentTask.setName(task.getName());
            currentTask.setEffort(task.getEffort());
        }
        return taskRepository.save(currentTask);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
