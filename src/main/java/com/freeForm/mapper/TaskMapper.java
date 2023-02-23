package com.freeForm.mapper;

import com.freeForm.dto.TaskDto;
import com.freeForm.entity.Task;

import java.util.List;
import java.util.stream.Collectors;

public class TaskMapper {

    public static TaskDto mapTaskToDto(Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setName(task.getName());
        taskDto.setEffort(task.getEffort());
        taskDto.setAttachment(AttachmentMapper.mapAttachmentsToDto(task.getAttachment()));
        return taskDto;
    }

    public static List<TaskDto> mapTasksToDtos(List<Task> tasks) {
        return tasks.stream().map(TaskMapper::mapTaskToDto).collect(Collectors.toList());
    }

}
