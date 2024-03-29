package com.freeForm.worker.mapper;

import com.freeForm.worker.dto.WorkerDto;
import com.freeForm.worker.dao.Worker;
import com.freeForm.task.mapper.TaskMapper;

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

        workerDto.setTasks(TaskMapper.mapTasksToDto(worker.getTasks()));
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

        worker.setTasks(TaskMapper.mapDtoToTasks(workerDto.getTasks()));
        return worker;
    }

}
