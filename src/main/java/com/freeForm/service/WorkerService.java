package com.freeForm.service;

import com.freeForm.dto.WorkerDto;
import com.freeForm.entity.Attachment;
import com.freeForm.entity.Task;
import com.freeForm.entity.Worker;
import com.freeForm.mapper.WorkerMapper;
import com.freeForm.repository.WorkerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class WorkerService {
    private final WorkerRepository workerRepository;

    public List<WorkerDto> getAllWorkers() {
        List<Worker> workers = workerRepository.findAll();
        return WorkerMapper.mapWorkersToDtos(workers);
    }

    public WorkerDto getWorkerById(Long id) {
        Worker worker = workerRepository.findById(id).orElseThrow(() -> new RuntimeException("Worker with id " + id + " not found"));
        return WorkerMapper.mapWorkerToDto(worker);
    }

    public WorkerDto createWorker(Worker worker, List<MultipartFile> files) throws IOException {
        List<Attachment> attachments = new ArrayList<>();
        for (MultipartFile file : files) {
            Attachment attachment = new Attachment();
            attachment.setName(file.getOriginalFilename());
            attachment.setData(file.getBytes());
            attachment.setContentType(file.getContentType());
            attachments.add(attachment);
        }
        worker.setTasks(worker.getTasks());
        worker.getTasks().forEach(task -> task.setAttachment(attachments.get(worker.getTasks().indexOf(task))));
        Worker createdWorker = workerRepository.save(worker);
        return WorkerMapper.mapWorkerToDto(createdWorker);
    }

    public WorkerDto updateWorker(Long id, Worker worker, List<MultipartFile> files) throws IOException {
        Worker currentWorker = workerRepository
                .findById(id)
                .orElseThrow(()->new RuntimeException("Worker with id " + id + " not found"));
        if (worker.getFirstname() != null) {
            currentWorker.setFirstname(worker.getFirstname());
        }
        if (worker.getLastname() != null) {
            currentWorker.setLastname(worker.getLastname());
        }
        if (worker.getAge() != null) {
            currentWorker.setAge(worker.getAge());
        }
        if (worker.getTasks() != null) {
            for (Task i : currentWorker.getTasks()) {
                for (Task j : worker.getTasks()) {
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
