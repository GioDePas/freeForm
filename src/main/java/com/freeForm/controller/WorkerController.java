package com.freeForm.controller;

import com.freeForm.dto.WorkerDto;
import com.freeForm.entity.Worker;
import com.freeForm.service.WorkerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/workers")
public class WorkerController {
    private final WorkerService workerService;

    @GetMapping
    @SecurityRequirement(name = "bearerAuth")
    public List<WorkerDto> getAllWorkers() {
        return workerService.getAllWorkers();
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public WorkerDto getWorkerById(@PathVariable Long id) {
        return workerService.getWorkerById(id);
    }

    @PostMapping
    @SecurityRequirement(name = "bearerAuth")
    public WorkerDto createWorker(
            @RequestPart Worker worker,
            @RequestPart List<MultipartFile> files
    ) throws IOException {
        return workerService.createWorker(worker, files);
    }

    @PutMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public WorkerDto updateWorker(
            @PathVariable Long id,
            @RequestPart Worker worker,
            @RequestPart List<MultipartFile> files
    ) throws IOException {
        return workerService.updateWorker(id, worker, files);
    }

    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public void deleteWorker(@PathVariable Long id) {
        workerService.deleteWorker(id);
    }
}
