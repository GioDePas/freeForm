package com.freeForm.controller;

import com.freeForm.dto.WorkerDto;
import com.freeForm.entity.Attachment;
import com.freeForm.entity.Worker;
import com.freeForm.mapper.WorkerMapper;
import com.freeForm.service.WorkerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/workers")
public class WorkerController {
    private final WorkerService workerService;

    @GetMapping
    @SecurityRequirement(name = "bearerAuth")
    public List<WorkerDto> getAllWorkers() {
        List<Worker> workers = workerService.getAllWorkers();
        return WorkerMapper.mapWorkersToDtos(workers);
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public WorkerDto getWorkerById(@PathVariable Long id) {
        Worker worker = workerService.getWorkerById(id);
        return WorkerMapper.mapWorkerToDto(worker);
    }

    @PostMapping
    @SecurityRequirement(name = "bearerAuth")
    public WorkerDto createWorker(
            @RequestPart Worker worker,
            @RequestPart List<MultipartFile> files
    ) throws IOException {
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
        Worker createdWorker = workerService.createWorker(worker);
        return WorkerMapper.mapWorkerToDto(createdWorker);
    }

    @PutMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public WorkerDto updateWorker(
            @PathVariable Long id,
            @RequestPart Worker worker,
            @RequestPart List<MultipartFile> files
    ) throws IOException {
        Worker updatedWorker = workerService.updateWorker(id, worker, files);
        return WorkerMapper.mapWorkerToDto(updatedWorker);
    }

    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public void deleteWorker(@PathVariable Long id) {
        workerService.deleteWorker(id);
    }
}
