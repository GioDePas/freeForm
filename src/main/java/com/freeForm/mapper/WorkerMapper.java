package com.freeForm.mapper;

import com.freeForm.dto.WorkerDto;
import com.freeForm.entity.Worker;

import java.util.List;
import java.util.stream.Collectors;

public class WorkerMapper {

    public static WorkerDto mapWorkerToDto(Worker worker) {
        WorkerDto workerDto = new WorkerDto();
        workerDto.setId(worker.getId());
        workerDto.setFirstname(worker.getFirstname());
        workerDto.setLastname(worker.getLastname());
        workerDto.setEmail(worker.getEmail());
        workerDto.setAge(worker.getAge());
        workerDto.setTasks(TaskMapper.mapTasksToDtos(worker.getTasks()));
        return workerDto;
    }

    public static List<WorkerDto> mapWorkersToDtos(List<Worker> workers) {
        return workers.stream().map(WorkerMapper::mapWorkerToDto).collect(Collectors.toList());
    }

}
