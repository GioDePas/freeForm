package com.freeForm.task.mapper;

import com.freeForm.task.dto.TaskDto;
import com.freeForm.task.dao.Task;

import java.util.List;
import java.util.stream.Collectors;

public class TaskMapper {

    public static TaskDto mapTaskToDto(Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setName(task.getName());
        taskDto.setEffort(task.getEffort());
        return taskDto;
    }

    public static List<TaskDto> mapTasksToDto(List<Task> tasks) {
        return tasks.stream().map(TaskMapper::mapTaskToDto).collect(Collectors.toList());
    }

    public static Task mapDtoToTask(TaskDto taskDto) {
        Task task = new Task();
        task.setId(taskDto.getId());
        task.setName(taskDto.getName());
        task.setEffort(taskDto.getEffort());
        return task;
    }

    public static List<Task> mapDtoToTasks(List<TaskDto> taskDto) {
        return taskDto.stream().map(TaskMapper::mapDtoToTask).collect(Collectors.toList());
    }

}
