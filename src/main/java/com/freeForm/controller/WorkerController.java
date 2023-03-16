package com.freeForm.controller;

import com.freeForm.dto.WorkerDto;
import com.freeForm.service.WorkerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
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
    private static final String ID_PATH = "/{id}";
    private static final String BEARER = "bearerAuth";

    @GetMapping
    @SecurityRequirement(name = BEARER)
    public List<WorkerDto> getAllWorkers() {
        return workerService.getAllWorkers();
    }

    @GetMapping(ID_PATH)
    @SecurityRequirement(name = BEARER)
    public WorkerDto getWorkerById(@PathVariable Long id) {
        return workerService.getWorkerById(id);
    }

    @PostMapping
    @SecurityRequirement(name = BEARER)
    public WorkerDto createWorker(
            @Valid
            @RequestPart WorkerDto workerDto,
            @RequestPart List<MultipartFile> files
    ) throws IOException {
        return workerService.createWorker(workerDto, files);
    }

    @PutMapping(ID_PATH)
    @SecurityRequirement(name = BEARER)
    public WorkerDto updateWorker(
            @PathVariable Long id,
            @RequestPart WorkerDto workerDto,
            @RequestPart List<MultipartFile> files
    ) throws IOException {
        return workerService.updateWorker(id, workerDto, files);
    }

    @DeleteMapping(ID_PATH)
    @SecurityRequirement(name = BEARER)
    public void deleteWorker(@PathVariable Long id) {
        workerService.deleteWorker(id);
    }
}
