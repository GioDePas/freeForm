package com.freeForm.service;

import com.freeForm.dto.WorkerDto;
import com.freeForm.entity.Attachment;
import com.freeForm.entity.Task;
import com.freeForm.entity.Worker;
import com.freeForm.errors.ErrorCodes;
import com.freeForm.errors.ErrorResponse;
import com.freeForm.errors.ErrorResponseList;
import com.freeForm.exceptions.ResourceNotFoundException;
import com.freeForm.mapper.WorkerMapper;
import com.freeForm.repository.TaskRepository;
import com.freeForm.repository.WorkerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkerService {
    private final WorkerRepository workerRepository;
    private final TaskRepository taskRepository;

    public List<WorkerDto> getAllWorkers() {
        List<Worker> workers = workerRepository.findAll();
        return WorkerMapper.mapWorkersToDto(workers);
    }

    public WorkerDto getWorkerById(Long id) {
        Worker worker = workerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                ErrorResponseList
                        .builder()
                        .errors(List.of(ErrorResponse
                                .builder()
                                .message(ErrorCodes.RESOURCE_NOT_FOUND.getMessage())
                                .code(ErrorCodes.RESOURCE_NOT_FOUND.getCode())
                                .build()))
                        .build()));
        return WorkerMapper.mapWorkerToDto(worker);
    }

    public WorkerDto createWorker(WorkerDto workerDto, List<MultipartFile> files) throws IOException{

        List<Attachment> attachments = new ArrayList<>();
        for (MultipartFile file : files) {
            Attachment attachment = new Attachment();
            attachment.setName(file.getOriginalFilename());
            attachment.setData(file.getBytes());
            attachment.setContentType(file.getContentType());
            attachments.add(attachment);
        }

        Worker worker = WorkerMapper.mapDtoToWorker(workerDto);
        Worker createdWorker = workerRepository.save(worker);

        List<Task> tasks = createdWorker.getTasks();
        if (tasks != null) {
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                task.setAttachment(attachments.get(i));
                taskRepository.save(task);
            }
        }
        return WorkerMapper.mapWorkerToDto(createdWorker);
    }

    public WorkerDto updateWorker(Long id, WorkerDto workerDto, List<MultipartFile> files) throws IOException {
        Worker currentWorker = workerRepository
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

        Worker workerToUpdate = WorkerMapper.mapDtoToWorker(workerDto);
        if (workerToUpdate.getFirstname() != null) {
            currentWorker.setFirstname(workerToUpdate.getFirstname());
        }
        if (workerToUpdate.getLastname() != null) {
            currentWorker.setLastname(workerToUpdate.getLastname());
        }
        if (workerToUpdate.getAge() != null) {
            currentWorker.setAge(workerToUpdate.getAge());
        }
        if (workerToUpdate.getTasks() != null) {
            for (Task i : currentWorker.getTasks()) {
                for (Task j : workerToUpdate.getTasks()) {
                    if (i.getId().equals(j.getId())) {
                        if (j.getName() != null) {
                            i.setName(j.getName());
                        }
                        if (j.getEffort() != null) {
                            i.setEffort(j.getEffort());
                        }
                    }
                }
            }
            if (files != null) {
                for (int i = 0; i < files.size(); i++) {
                    Attachment attachment = currentWorker.getTasks().get(i).getAttachment();
                    attachment.setName(files.get(i).getOriginalFilename());
                    attachment.setData(files.get(i).getBytes());
                    attachment.setContentType(files.get(i).getContentType());
                    currentWorker.getTasks().get(i).setAttachment(attachment);
                }
            }
        }
        Worker updatedWorker = workerRepository.save(currentWorker);
        return WorkerMapper.mapWorkerToDto(updatedWorker);
    }

    public void deleteWorker(Long id) {
        workerRepository.deleteById(id);
    }
}
