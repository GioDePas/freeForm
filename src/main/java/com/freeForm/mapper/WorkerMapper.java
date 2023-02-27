package com.freeForm.mapper;

import com.freeForm.dto.TaskDto;
import com.freeForm.dto.WorkerDto;
import com.freeForm.entity.Task;
import com.freeForm.entity.Worker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TransferQueue;
import java.util.stream.Collectors;

public class WorkerMapper {

    public static WorkerDto mapWorkerToDto(Worker worker) {

        WorkerDto workerDto = new WorkerDto();

        workerDto.setId(worker.getId());
        workerDto.setFirstname(worker.getFirstname());
        workerDto.setLastname(worker.getLastname());
        workerDto.setEmail(worker.getEmail());
        workerDto.setAge(worker.getAge());

        List<TaskDto> taskDtos = new ArrayList<>();
        for(Task task : worker.getTasks()) {
            TaskDto taskDto = new TaskDto();
            taskDto.setId(task.getId());
            taskDto.setName(task.getName());
            taskDto.setEffort(task.getEffort());
            taskDtos.add(taskDto);
        }
        workerDto.setTasks(taskDtos);
        return workerDto;
    }

    public static List<WorkerDto> mapWorkersToDto(List<Worker> workers) {
        return workers.stream().map(WorkerMapper::mapWorkerToDto).collect(Collectors.toList());
    }

    public static Worker mapDtoToWorker(WorkerDto workerDto) {
        Worker worker = new Worker();
        worker.setFirstname(workerDto.getFirstname());
        worker.setLastname(workerDto.getLastname());
        worker.setEmail(workerDto.getEmail());
        worker.setAge(workerDto.getAge());

        List<Task> tasks = new ArrayList<>();
        for(TaskDto taskDto : workerDto.getTasks()) {
            Task task =  new Task();
            task.setName(taskDto.getName());
            task.setEffort(taskDto.getEffort());
            tasks.add(task);
        }
        worker.setTasks(tasks);
        return worker;
    }

}
